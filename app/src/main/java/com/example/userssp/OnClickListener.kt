package com.example.userssp

//Va a estar siempre escuchando un evento
interface OnClickListener {
    fun onClick(user: User, position: Int)
}