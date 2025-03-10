package uni.project.mylocalbooking.activities.client;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import uni.project.mylocalbooking.MyLocalBooking;
import uni.project.mylocalbooking.R;
import uni.project.mylocalbooking.models.Establishment;
import uni.project.mylocalbooking.models.ITimeFrame;
import uni.project.mylocalbooking.models.ManualSlot;
import uni.project.mylocalbooking.models.PeriodicSlot;
import uni.project.mylocalbooking.models.Slot;

public class Adapter_myBookings extends RecyclerView.Adapter<Adapter_myBookings.ViewHolder2>{
    public interface IReservationSelectedListener {
        void onReservationSelected(Slot slot);
    }
    private final List<Slot> userBookings;
    private final IReservationSelectedListener listener;

    public Adapter_myBookings(IReservationSelectedListener listener, List<Slot> slots) {
        this.userBookings = slots;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Adapter_myBookings.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mybookings_row, parent, false);
        return new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_myBookings.ViewHolder2 holder, int position) {
        int resource = R.drawable.logo;
        holder.setData(resource, userBookings.get(position));
    }

    @Override
    public int getItemCount() {
        return userBookings.size();
    }



    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Establishment establishment;
        private Slot slot;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);


            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        public void setData(int resource, Slot slot) {
            this.slot = slot;
            ((ImageView) itemView.findViewById(R.id.my_bookings_image)).setImageResource(resource);
            establishment = slot.blueprint.establishment;
            ((TextView) itemView.findViewById(R.id.name_location_myBookings)).setText(establishment.name);
            ((TextView) itemView.findViewById(R.id.position_myBookings)).setText(establishment.address);
            ((TextView) itemView.findViewById(R.id.hour_myBookings))
                    .setText(slot.getStart().toString() + " - " + slot.getEnd().toString());
        }

        @Override
        public void onClick(View view) {
            listener.onReservationSelected(slot);
        }

    }

}
