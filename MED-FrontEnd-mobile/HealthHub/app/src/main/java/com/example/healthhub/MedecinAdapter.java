package com.example.healthhub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//**MedecinAdapter**	Adaptateur pour RecyclerView : il sert à afficher la liste des médecins dans une vue en liste, et gérer les clics.
public class MedecinAdapter extends RecyclerView.Adapter<MedecinAdapter.MedecinViewHolder> {

    private List<Medecin> medecins;
    private OnMedecinClickListener listener;

    public MedecinAdapter(List<Medecin> medecins, OnMedecinClickListener listener) {
        this.medecins = medecins;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MedecinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemedecin, parent, false);
        return new MedecinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MedecinViewHolder holder, int position) {
        Medecin medecin = medecins.get(position);
        holder.nom.setText(medecin.getNom());
        holder.specialite.setText(medecin.getSpecialite());
        holder.image.setImageResource(medecin.getImageResId());
        holder.ratingBar.setRating(medecin.getRating());
        holder.lieuTravail.setText("Travaille à : " + medecin.getLieuTravail());
        holder.experience.setText("Expérience : " + medecin.getExperienceAnnees() + " ans");

        holder.itemView.setOnClickListener(v -> listener.onMedecinClick(medecin));
    }

    @Override
    public int getItemCount() {
        return medecins.size();
    }

    static class MedecinViewHolder extends RecyclerView.ViewHolder {
        TextView nom, specialite, lieuTravail, experience;
        ImageView image;
        RatingBar ratingBar;

        MedecinViewHolder(View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nomMedecin);
            specialite = itemView.findViewById(R.id.specialiteMedecin);
            image = itemView.findViewById(R.id.imgMedecin);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            lieuTravail = itemView.findViewById(R.id.lieuTravailMedecin);
            experience = itemView.findViewById(R.id.experienceMedecin);
        }
    }
}
