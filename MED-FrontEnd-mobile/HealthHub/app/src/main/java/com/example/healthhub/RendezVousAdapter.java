package com.example.healthhub;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RendezVousAdapter extends RecyclerView.Adapter<RendezVousAdapter.RendezVousViewHolder> {
    private List<rendezvous> listeRendezVous;

    public static class RendezVousViewHolder extends RecyclerView.ViewHolder {
        TextView nomMedecin, specialite, date, heure;

        public RendezVousViewHolder(View itemView) {
            super(itemView);
            nomMedecin = itemView.findViewById(R.id.tvNomMedecin);
            specialite = itemView.findViewById(R.id.tvSpecialite);
            date = itemView.findViewById(R.id.tvDate);
            heure = itemView.findViewById(R.id.tvHeure);
        }
    }

    public RendezVousAdapter(List<rendezvous> listeRendezVous) {
        this.listeRendezVous = listeRendezVous;
    }

    @Override
    public RendezVousViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vue = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rendezvous, parent, false);
        return new RendezVousViewHolder(vue);
    }

    @Override
    public void onBindViewHolder(RendezVousViewHolder holder, int position) {
        rendezvous rdv = listeRendezVous.get(position);
        holder.nomMedecin.setText(rdv.getNomMedecin());
        holder.specialite.setText(rdv.getSpecialite());
        holder.date.setText(rdv.getDate());
        holder.heure.setText(rdv.getHeure());
    }

    @Override
    public int getItemCount() {
        return listeRendezVous.size();
    }
}
