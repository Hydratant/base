package com.tami.adapter

import androidx.recyclerview.widget.RecyclerView

abstract class SingleAdapter<VH : RecyclerView.ViewHolder, VD> : RecyclerView.Adapter<VH>() {

    val lock = Any()
    var items: MutableList<VD> = mutableListOf()

    var onItemClick: ((item: VD) -> Unit)? = null
    var onItemLongClick: ((item: VD) -> Unit)? = null

    abstract fun onBind(holder: VH, item: VD)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClick?.invoke(item) ?: return@setOnLongClickListener false

            return@setOnLongClickListener true
        }
        onBind(holder, item)
    }

    fun set(items: MutableList<VD>) {
        synchronized(lock) {
            this@SingleAdapter.items = items
        }
        notifyDataSetChanged()
    }

    fun add(item: VD) {
        synchronized(lock) {
            items.add(item)
            notifyItemInserted(items.lastIndex)
        }
    }


    fun addAll(item: MutableList<VD>) {
        synchronized(lock) {
            val orgLast = items.size
            items.addAll(item)
            notifyItemInserted(items.lastIndex)
            notifyItemRangeInserted(orgLast, item.size)
        }
    }


    fun remove(item: VD) {
        synchronized(lock) {
            items.remove(item)
        }
        notifyDataSetChanged()
    }

    fun removeByIndex(index: Int) {
        synchronized(lock) {
            items.removeAt(index)
            notifyItemRemoved(index)
            notifyItemRangeChanged(index, items.size)
        }
    }

    fun clear() {
        synchronized(lock) {
            val size = items.size
            items.clear()
            notifyItemRangeRemoved(0, size)
        }
    }

    fun getItem(position: Int): VD = items[position]
}