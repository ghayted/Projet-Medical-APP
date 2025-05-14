import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './MyConsultation.css';
import { MessageSquare, Calendar, Clock, User, Stethoscope } from 'lucide-react';

const MyConsultation = () => {
  const [consultations, setConsultations] = useState([]);
  const [messages, setMessages] = useState([]);
  const [error, setError] = useState('');
  const [activeTab, setActiveTab] = useState('Pending');
  const [selectedConsultation, setSelectedConsultation] = useState(null);
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(true);

  const userId = localStorage.getItem('userId');
  const userType = localStorage.getItem('userType');

  // Helper function for fallback text
  const safeText = (value, fallback = 'N/A') => {
    return value && value.trim() !== '' ? value : fallback;
  };

  // Format date (YYYY-MM-DD)
  const formatDate = (dateString) => {
    if (!dateString) return 'N/A';
    const date = new Date(dateString);
    return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;
  };

  // Format time (HH:MM)
  const formatTime = (dateString) => {
    if (!dateString) return '';
    const date = new Date(dateString);
    return `${String(date.getHours()).padStart(2, '0')}:${String(date.getMinutes()).padStart(2, '0')}`;
  };

  // Function to fetch messages
  const fetchMessages = () => {
    if (selectedConsultation) {
      const patientId = selectedConsultation.patient?.id;
      const doctorId = selectedConsultation.docteur?.id;

      if (patientId && doctorId) {
        axios
          .get(`http://localhost:8080/api/messages/between?docteurId=${doctorId}&patientId=${patientId}`)
          .then((res) => {
            setMessages(res.data);
          })
          .catch((err) => {
            console.error('Error loading messages:', err);
            setError('Failed to load messages. Please try again.');
          });
      }
    }
  };

  // Load consultations
  useEffect(() => {
    if (!userId || !userType) {
      setError('User session not found. Please login again.');
      setLoading(false);
      return;
    }

    const url =
      userType === 'patient'
        ? `http://localhost:8080/api/consultations/patient/${userId}`
        : `http://localhost:8080/api/consultations/doctor/${userId}`;

    setLoading(true);
    axios
      .get(url)
      .then((res) => {
        setConsultations(res.data);
        if (res.data.length > 0) {
          setSelectedConsultation(res.data[0]);
        }
        setLoading(false);
      })
      .catch((err) => {
        setError('Failed to load consultations. Please try again later.');
        console.error('Error loading consultations:', err);
        setLoading(false);
      });
  }, [userId, userType]);

  // Load messages when consultation changes
  useEffect(() => {
    setMessages([]);
    fetchMessages();
  }, [selectedConsultation]);

  useEffect(() => {
    const interval = setInterval(() => {
      fetchMessages();
    }, 1000);

    return () => clearInterval(interval);
  }, [selectedConsultation]); 

  useEffect(() => {
    const chatBox = document.querySelector('.chat-messages');
    if (chatBox) {
      chatBox.scrollTop = chatBox.scrollHeight;
    }
  }, [messages]);

  const handleSendMessage = async () => {
    if (!message.trim()) return;
  
    let consultation = selectedConsultation;
  
    try {
      // Step 1: If no consultation is selected, create one
      if (!consultation) {
        const newConsultation = {
          docteurId: userType === 'patient' ? 'targetDoctorId' : userId, // you must dynamically resolve this
          patientId: userType === 'patient' ? userId : 'targetPatientId',
          reason: 'Message Initiated Chat',
          status: 'pending',
          dateConsultation: new Date().toISOString()
        };
  
        const res = await axios.post('http://localhost:8080/api/consultations', newConsultation);
        consultation = res.data;
        setConsultations((prev) => [...prev, consultation]);
        setSelectedConsultation(consultation);
      }
  
      // Step 2: Ensure ordonnance exists for the consultation
      let ordonnanceId = consultation.ordonnance?.id;
  
      if (!ordonnanceId) {
        const ordonnanceResponse = await axios.post(`http://localhost:8080/api/ordonnances`, {
          consultationId: consultation.id
        });

        const updatedOrdonnance = ordonnanceResponse.data;

        // Fetch updated consultation that includes the ordonnance
        const consultationResponse = await axios.get(`http://localhost:8080/api/consultations/${consultation.id}`);
        consultation = consultationResponse.data;
        setSelectedConsultation(consultation);
        ordonnanceId = updatedOrdonnance.id;
      }
  
      // Step 3: Send the message
      const dto = {
        docteurId: consultation.docteur?.id,
        patientId: consultation.patient?.id,
        ordonnanceId: ordonnanceId,
        senderType: userType,
        receiverType: userType === 'doctor' ? 'patient' : 'doctor',
        messageText: message,
        mediaUrl: null,
        mediaType: null
      };
  
      const res = await axios.post('http://localhost:8080/api/messages', dto);
      setMessages((prev) => [...prev, res.data]);
      setMessage('');
    } catch (err) {
      console.error('Message sending or consultation creation failed:', err);
      setError('Failed to send message. Please try again.');
    }
  };
  

  const getCounterpartyName = (consultation) => {
    if (userType === 'patient') {
      return consultation.docteur
        ? `Dr. ${consultation.docteur.prenom} ${consultation.docteur.nom}`
        : 'Doctor';
    } else {
      return consultation.patient
        ? `${consultation.patient.prenom} ${consultation.patient.nom}`
        : 'Patient';
    }
  };

  const getCounterpartyDetails = (consultation) => {
    if (userType === 'patient') {
      return {
        name: `Dr. ${consultation.docteur?.prenom} ${consultation.docteur?.nom}`,
        specialty: safeText(consultation.docteur?.speciality, 'General'),
        icon: <Stethoscope size={20} />
      };
    } else {
      return {
        name: `${consultation.patient?.prenom} ${consultation.patient?.nom}`,
        details: `Age: ${consultation.patient?.age || 'N/A'} | Gender: ${consultation.patient?.gender || 'N/A'}`,
        icon: <User size={20} />
      };
    }
  };

  const filteredConsultations = consultations.filter((consultation) => {
    if (activeTab === 'Pending') return consultation.status === 'pending' || !consultation.status;
    if (activeTab === 'Accepted') return consultation.status === 'accepted';
    if (activeTab === 'Completed') return consultation.status === 'completed';
    return true;
  });

  const isMessageFromDoctor = (msg) => {
    return msg.senderType === 'doctor';
  };
  
  const isMessageFromCurrentUser = (msg) => {
    return msg.senderType === userType;
  };

  return (
    <div className="dashboard-container">
      <div className="dashboard-content">
        <div className="dashboard-layout">
          {/* Consultation List */}
          <div className="consultations-list-container">
            <div className="tabs">
              {['Pending', 'Accepted', 'Completed'].map((tab) => (
                <button
                  key={tab}
                  className={`tab ${activeTab === tab ? 'active' : ''}`}
                  onClick={() => setActiveTab(tab)}
                >
                  {tab}
                </button>
              ))}
            </div>

            <h3 className="list-title">
              {userType === 'patient' ? 'My Consultations' : 'Patient Consultations'}
            </h3>

            <div className="consultations-list">
              {error && <p className="error-message">{error}</p>}
              {loading && <p className="loading">Loading consultations...</p>}
              {!loading && filteredConsultations.length === 0 ? (
                <p className="no-consultations">No consultations found</p>
              ) : (
                filteredConsultations.map((consultation) => (
                  <div
                    className={`consultation-item ${selectedConsultation?.id === consultation.id ? 'selected' : ''}`}
                    key={consultation.id}
                    onClick={() => setSelectedConsultation(consultation)}
                  >
                    <div className="consultation-details">
                      <h4 className="counterparty-name">{getCounterpartyName(consultation)}</h4>
                      {userType === 'doctor' && (
                        <p className="patient-age">Age: {consultation.patient?.age || 'N/A'}</p>
                      )}
                      <div className="consultation-time">
                        <Calendar className="icon" size={16} />
                        <span>{formatDate(consultation.dateConsultation)}</span>
                        <Clock className="icon" size={16} />
                        <span>{formatTime(consultation.dateConsultation)}</span>
                      </div>
                    </div>
                    <div className="status-badge">{consultation.status || 'pending'}</div>
                  </div>
                ))
              )}
            </div>
          </div>

          {/* Consultation Details */}
          {selectedConsultation && (
            <div className="consultation-detail">
              <div className="consultation-header">
                <div>
                  {(() => {
                    const counterparty = getCounterpartyDetails(selectedConsultation);
                    return (
                      <>
                        <div className="header-with-icon">
                          {counterparty.icon}
                          <h3 className="detail-name">{counterparty.name}</h3>
                        </div>
                        <p className="detail-info">
                          {userType === 'patient' 
                            ? `Specialty: ${counterparty.specialty}`
                            : counterparty.details}
                        </p>
                      </>
                    );
                  })()}
                  <p className="appointment-time">
                    <Calendar className="icon" size={16} />
                    {formatDate(selectedConsultation.dateConsultation)} at{' '}
                    <Clock className="icon" size={16} />
                    {formatTime(selectedConsultation.dateConsultation)}
                  </p>
                </div>
                <div className="appointment-status">
                  Status: <span className="status">{selectedConsultation.status || 'pending'}</span>
                </div>
              </div>

              <div className="consultation-reason">
                <h4>Reason for Consultation</h4>
                <p>{selectedConsultation.reason || 'No reason provided'}</p>
              </div>

              {userType === 'doctor' && (
                <div className="medical-notes">
                  <h4>Medical Notes</h4>
                  <p>{selectedConsultation.notes || 'No notes available yet'}</p>
                </div>
              )}

              <div className="chat-section">
                <div className="chat-header">
                  <MessageSquare size={18} />
                  <span>Messages</span>
                </div>

                <div className="chat-messages">
                  {messages.length === 0 ? (
                    <p className="no-messages">No messages yet. Start the conversation.</p>
                  ) : (
                    messages.map((msg, index) => {
                      const isFromDoctor = isMessageFromDoctor(msg);
                      const isFromMe = isMessageFromCurrentUser(msg);
                      
                      let senderName;
                      if (isFromDoctor) {
                        senderName = userType === 'doctor' 
                          ? 'You' 
                          : `Dr. ${msg.docteur?.prenom || ''} ${msg.docteur?.nom || ''}`.trim();
                      } else {
                        senderName = userType === 'patient'
                          ? 'You'
                          : `${msg.patient?.prenom || ''} ${msg.patient?.nom || ''}`.trim();
                      }
                      
                      if (!senderName || senderName === 'You  ' || senderName === ' ') {
                        senderName = isFromDoctor ? 'Doctor' : 'Patient';
                      }
                      
                      return (
                        <div
                          key={index}
                          className={`chat-bubble ${isFromMe ? 'sent' : 'received'}`}
                        >
                          <div className="message-header">
                            <span className="message-sender">{senderName}</span>
                          </div>
                          <p className="message-text">{msg.messageText}</p>
                          <span className="message-time">
                            {msg.timestamp ? formatTime(msg.timestamp) : ''}
                          </span>
                        </div>
                      );
                    })
                  )}
                </div>

                <div className="chat-input">
                  <input
                    type="text"
                    placeholder="Type your message..."
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    onKeyPress={(e) => e.key === 'Enter' && handleSendMessage()}
                  />
                  <button className="btn-send" onClick={handleSendMessage}>
                    Send
                  </button>
                </div>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default MyConsultation;