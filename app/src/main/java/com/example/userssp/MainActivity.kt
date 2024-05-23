package com.example.userssp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userssp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        userAdapter = UserAdapter(getUsers()) //userAdapter recibe un Listado
        linearLayoutManager = LinearLayoutManager(this) //

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }

    private fun getUsers(): MutableList<User>{
        val users = mutableListOf<User>()

        val leo = User(1, "Leo", "Largo", "https://live.staticflickr.com/3175/2627394629_6f86ff889a_b.jpg")
        val ale = User(2, "Ale", "Macu", "https://media.licdn.com/dms/image/C5603AQE9pPpHveR5Gg/profile-displayphoto-shrink_200_200/0/1517700692492?e=2147483647&v=beta&t=AyNlobeggNY-57JFK4Hwg4L6zsV5ywL27lEr8rd8NaU")
        val juan = User(3, "Juan", "Ere", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTCYFLL7WZDj9bSqGzIxvj7z7bNRyjh6UvsiP3sycW97w&s")
        val maria = User(4, "Maria", "Rez", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRE_XSEvHwDY3Z47RJtnDtm9Pw1wrzWXiv_f6c1U8joVg&s")


        users.add(leo) //agregamos los datos a mutableList
        users.add(ale)
        users.add(juan)
        users.add(maria)
        users.add(leo) //agregamos los datos a mutableList
        users.add(ale)
        users.add(juan)
        users.add(maria)
        users.add(leo) //agregamos los datos a mutableList
        users.add(ale)
        users.add(juan)
        users.add(maria)
        users.add(leo) //agregamos los datos a mutableList
        users.add(ale)
        users.add(juan)
        users.add(maria)
        users.add(leo) //agregamos los datos a mutableList
        users.add(ale)
        users.add(juan)
        users.add(maria)
        return users
    }
}