package com.merabk.moviesapplicationtm.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merabk.moviesapplicationtm.R
import com.merabk.moviesapplicationtm.databinding.RvItemBinding
import com.merabk.moviesapplicationtm.domain.model.MainContentDomainModel
import com.merabk.moviesapplicationtm.util.loadImageWithGlide

class RvAdapter(
    private val movieItemClickListener: MovieItemClickListener
) : ListAdapter<MainContentDomainModel, RvAdapter.TvViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder = TvViewHolder(
        RvItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), movieItemClickListener
    )

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) =
        holder.bind(getItem(position))

    class TvViewHolder(
        private val binding: RvItemBinding,
        private val movieItemClickListener: MovieItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(rvModel: MainContentDomainModel) = with(binding) {
            filmImage.loadImageWithGlide(rvModel.posterPath)
            filmName.text = root.context.getString(R.string.name_str, rvModel.name)
            filmRating.text =
                root.context.getString(R.string.rating_str, rvModel.voteAverage.toString())
            itemView.setOnClickListener {
                movieItemClickListener.onMovieItemClicked(rvModel.id)
            }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<MainContentDomainModel>() {

            override fun areContentsTheSame(
                oldItem: MainContentDomainModel,
                newItem: MainContentDomainModel
            ): Boolean = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: MainContentDomainModel,
                newItem: MainContentDomainModel
            ): Boolean = oldItem.id == newItem.id
        }
    }

    interface MovieItemClickListener {
        fun onMovieItemClicked(movieId: Int)
    }
}