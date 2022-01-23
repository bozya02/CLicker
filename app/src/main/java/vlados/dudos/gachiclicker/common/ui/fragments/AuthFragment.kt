package vlados.dudos.gachiclicker.common.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import vlados.dudos.gachiclicker.R
import vlados.dudos.gachiclicker.common.ui.activity.LoginActivity
import vlados.dudos.gachiclicker.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private lateinit var b: FragmentAuthBinding
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentAuthBinding.bind(
            inflater.inflate(
                R.layout.fragment_auth, container, false
            )
        )
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        b.btnToReg.setOnClickListener {
            (activity as LoginActivity).fragmentTransaction(RegistrationFragment())
        }
        b.doneBtn.setOnClickListener {

        }
        b.forgotPasswordTxt.setOnClickListener {

        }
    }
}