package com.example.userssp

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userssp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE) //Almacenamiento

        //inicializar preferences
        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)
        Log.i("SP", "${getString(R.string.sp_first_time)} = $isFirstTime")

        //Almacenar dato permanentemente
        if (isFirstTime) {
            val dialogView = layoutInflater.inflate(R.layout.dialos_registrer, null)
            /*MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.dialog_title))
                .setView(dialogView)
                .setCancelable(false) //eso hace que el dialog no se cancele
                .setPositiveButton(R.string.dialog_confirm) { _, _ ->
                    //extraer lo insertado en el editText y guardar env val
                    val userName = dialogView.findViewById<TextInputEditText>(R.id.edUserName)
                        .text.toString()
                    with(preferences.edit()) {
                        putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), userName)
                            //insertar datos
                            .apply()
                    }
                    Toast.makeText(this, R.string.register_ok, Toast.LENGTH_SHORT).show()
                }
                .show()*/
            //Validar Nombre de usuario
            val dialog = MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.dialog_title))
                .setView(dialogView)
                .setCancelable(false) //eso hace que el dialog no se cancele
                .setPositiveButton(R.string.dialog_confirm) { _, _ ->}
                .create()

            dialog.show() //primero se debe mostrar el dialog

            dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                //extraer lo insertado en el editText y guardar env val
                val userName = dialogView.findViewById<TextInputEditText>(R.id.edUserName)
                    .text.toString()
                if (userName.isBlank()){//validacion de registro
                    Toast.makeText(this, R.string.register_invalid, Toast.LENGTH_SHORT).show()
                }else {
                    with(preferences.edit()) {
                        putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), userName)
                            //insertar datos
                            .apply()
                    }
                    Toast.makeText(this, R.string.register_ok, Toast.LENGTH_SHORT).show()
                    dialog.dismiss() //manda a cerrar el dialog
                }
            }

        }else {
            val username = preferences.getString(getString(R.string.sp_username), getString(R.string.hint_username))
            Toast.makeText(this, "Bienvenido al imalaya $username", Toast.LENGTH_SHORT).show()
        }

        //userAdapter recibe un Listado
        userAdapter = UserAdapter(getUsers(), this)
        linearLayoutManager = LinearLayoutManager(this) //

        binding.recyclerView.apply {
            setHasFixedSize(true) //se indica que las vistas tienen un tamaño definido
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }

        //Eliminar del listado con SWIPE
        val swipeHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback
            (0, ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                    target: RecyclerView.ViewHolder): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //se define el comportamiento del SWIPE
                userAdapter.remove(viewHolder.adapterPosition)//devuelve la ubicacion actual del list
            }
        })
        swipeHelper.attachToRecyclerView(binding.recyclerView)
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

    override fun onClick(user: User, position: Int) {
        //el mensaje muestra que posision ocupa del listado y el nombre del usueario completo
        Toast.makeText(this, "$position: ${user.getFullName()}" , Toast.LENGTH_SHORT).show()
    }
}