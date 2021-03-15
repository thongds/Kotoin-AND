package com.example.tokoinand.commonModel

data class NewsListModel(val status : String?,val totalResults : Int?,val articles : List<ArticleItem>)
data class Source(val id : String?,val name : String?)
data class ArticleItem(val source : Source?,val author : String?,val title : String?,val description : String?,
                       val url : String?,val urlToImage : String?,val publishedAt : String?,val content : String?)