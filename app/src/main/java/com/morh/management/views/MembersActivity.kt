package com.morh.management.views

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.morh.management.models.Member
import com.morh.management.viewmodels.LoginViewModel
import com.morh.management.viewmodels.MembersViewModel
import com.morh.protocolmanagement.R
import com.morh.protocolmanagement.databinding.ActivityMainBinding
import com.morh.protocolmanagement.databinding.ActivityMembersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MembersActivity : ComponentActivity() {
    private lateinit var binding: ActivityMembersBinding
    private lateinit var _membersViewModel: MembersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // MVVM Pattern Binds View to ViewModel
        binding = ActivityMembersBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_members)

        _membersViewModel = ViewModelProvider(this)[MembersViewModel::class]

        var members : List<Member>? = null
        val job = CoroutineScope(Dispatchers.Default).launch {
            members = _membersViewModel.getMembers()
        }
        runBlocking {
            job.join()
        }
    }
}