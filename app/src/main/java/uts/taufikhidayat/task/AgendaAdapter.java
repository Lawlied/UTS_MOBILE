package uts.taufikhidayat.task;

import android.graphics.Color; // Import Color
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.AgendaViewHolder> {
    private List<Agenda> agendaList;

    public AgendaAdapter(List<Agenda> agendaList) {
        this.agendaList = agendaList;
    }

    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda, parent, false);
        return new AgendaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaViewHolder holder, int position) {
        Agenda agenda = agendaList.get(position);
        holder.textViewTitle.setText(agenda.getTitle());
        holder.textViewDescription.setText(agenda.getDescription());

        // Set warna teks berdasarkan status
        if ("selesai".equalsIgnoreCase(agenda.getStatus())) {
            holder.textViewStatus.setTextColor(Color.GREEN); // Hijau untuk selesai
        } else if ("belum selesai".equalsIgnoreCase(agenda.getStatus())) {
            holder.textViewStatus.setTextColor(Color.RED); // Merah untuk belum selesai
        }

        holder.textViewStatus.setText(agenda.getStatus());
    }

    @Override
    public int getItemCount() {
        return agendaList.size();
    }

    public byte[] getName() {
        return new byte[0];
    }

    public byte[] getDescription() {
        return new byte[0];
    }

    public byte[] getStatus() {
        return new byte[0];
    }

    static class AgendaViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDescription, textViewStatus;

        public AgendaViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewStatus = itemView.findViewById(R.id.textViewStatus);
        }
    }
}
