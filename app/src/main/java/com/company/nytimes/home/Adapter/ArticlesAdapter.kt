package com.company.nytimes.home.Adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.domain.model.Articles
import com.company.nytimes.databinding.ArticlesItemBinding

class ArticlesAdapter(
    private val clickCallBack: (articles: Articles) -> Unit,
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private lateinit var binding:ArticlesItemBinding
    private var articles = ArrayList<Articles>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ArticlesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var article =articles[position]
        holder.title.text=article.mTitle
        holder.byLine.text=article.mByline
        holder.source.text=article.mSource
        holder.date.text=article.mPublishedDate
        holder.itemView.setOnClickListener {
            clickCallBack.invoke(articles[position])
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    inner class ViewHolder(itemView: ArticlesItemBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val title = itemView.title
        val byLine = itemView.by
        val source= itemView.source
        val date= itemView.date
        val image=itemView.image

    }

    fun setData(data: ArrayList<Articles>) {
        this.articles.clear()
        this.articles = data ?: ArrayList()
        repeat(articles.size) {
            notifyItemChanged(it)
        }
    }


}