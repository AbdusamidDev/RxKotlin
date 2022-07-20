package developer.abdusamid.rxkotlin.dao

import developer.abdusamid.rxkotlin.entity.User

interface OnItemCLickListener{
        fun onItemClick(user: User)
    }