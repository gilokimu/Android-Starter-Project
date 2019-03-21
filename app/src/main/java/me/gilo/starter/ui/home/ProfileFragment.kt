package me.gilo.starter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.gilo.starter.R
import me.gilo.starter.viewmodels.UserViewModel


class ProfileFragment : Fragment() {


    lateinit var viewModel: UserViewModel
    val TAG = "ProfileFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    companion object {
        @JvmStatic
        fun newInstance() =
                ProfileFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }

}
