package com.sera5.narawara.ListArtikel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.bugsnag.android.Bugsnag;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sera5.narawara.R;
import com.sera5.narawara.ViewArtikelActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.MyViewHolder> implements Filterable {

    private List<Artikel> moviesList;
    public MyFilter mFilter;
    SimpleDateFormat df,df2;

    private List<Artikel> mFilteredList;

    public ArtikelAdapter(List<Artikel> moviesList, Context context) {
        this.moviesList = moviesList;
        this.mFilteredList = new ArrayList<>();

       df  = new SimpleDateFormat("yyyyMMddHHmmss",Locale.US);
       df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",Locale.US);

    }

    @Override
    public Filter getFilter() {

        if (mFilter == null) {
            mFilteredList.clear();
            mFilteredList.addAll(this.moviesList);
            mFilter = new ArtikelAdapter.MyFilter(this, mFilteredList);
        }
        return mFilter;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Artikel movie = moviesList.get(position);
        holder.img.setImageURI(movie.getImg());
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getShort_description());

        if(!movie.getImg().isEmpty()) {
            holder.img.setImageURI(movie.getImg());
        }

        try {
            Date date = df.parse(String.valueOf(movie.getPublish_date()));
            holder.publish_date.setText(df2.format(date));
        } catch (ParseException e) {
            holder.publish_date.setText(movie.getPublish_date());
            Bugsnag.notify(new RuntimeException("Failed to parse date: ", e));
        }


        holder.author.setText(movie.getAuthor());

        holder.read_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewArtikelActivity.class);
                intent.putExtra("news_id", movie.getId());
                v.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_artikel, parent, false);

        return new MyViewHolder(itemView);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, publish_date, author, url;
        public SimpleDraweeView img;
        Button read_more;

        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.txt_description);
            publish_date = view.findViewById(R.id.txt_date);
            author = view.findViewById(R.id.txt_author);
            img = view.findViewById(R.id.img);
            read_more = view.findViewById(R.id.readmore);
        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    private static class MyFilter extends Filter {

        private final ArtikelAdapter myAdapter;
        private final List<Artikel> originalList;
        private final List<Artikel> filteredList;

        private MyFilter(ArtikelAdapter myAdapter, List<Artikel> originalList) {
            this.myAdapter = myAdapter;
            this.originalList = originalList;
            this.filteredList = new ArrayList<Artikel>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            filteredList.clear();
            final FilterResults results = new FilterResults();
            if (charSequence.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                final String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Artikel artikel : originalList) {
                    if (artikel.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(artikel);
                    }
                }
            }

            results.values = filteredList;
            results.count = filteredList.size();
            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            myAdapter.moviesList.clear();
            boolean b = myAdapter.moviesList.addAll((ArrayList<Artikel>) filterResults.values);
            myAdapter.notifyDataSetChanged();

        }
    }
}

