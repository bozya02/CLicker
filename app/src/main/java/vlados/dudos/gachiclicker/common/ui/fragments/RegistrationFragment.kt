package vlados.dudos.gachiclicker.common.ui.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import vlados.dudos.gachiclicker.R
import vlados.dudos.gachiclicker.common.ui.activity.LoginActivity
import vlados.dudos.gachiclicker.common.ui.models.User
import vlados.dudos.gachiclicker.databinding.FragmentRegBinding

class RegistrationFragment : Fragment() {

    private lateinit var b: FragmentRegBinding
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentRegBinding.bind(
            inflater.inflate(R.layout.fragment_reg, container, false)
        )
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    private fun onClick() {
        b.btnToAuth.setOnClickListener {
            (activity as LoginActivity).fragmentTransaction(AuthFragment())
        }
        b.doneBtn.setOnClickListener {
            val nick = b.inputNick.text.toString()
            val mail = b.inputEmail.text.toString()
            val password = b.inputPassword.text.toString()

            if (checkInput()) {
                auth.createUserWithEmailAndPassword(
                    mail,
                    password
                )
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val user = User(nick, mail, password, 0, 1, 0)
                            FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                                .setValue(user).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        makeToast("You have been register successfully")
                                    }
                                    else makeToast("Something went wrong")
                                }

                        } else makeToast(task.exception!!.message.toString())
                    }

                (activity as LoginActivity).fragmentTransaction(AuthFragment())
            }
        }
    }

    private fun checkInput(): Boolean {
        when {
            !Patterns.EMAIL_ADDRESS.matcher(b.inputEmail.text)
                .matches() -> makeToast("Input correct email")
            b.inputEmail.text.isNullOrEmpty() -> makeToast("Input your email")
            b.inputNick.text.isNullOrEmpty() -> makeToast("Input your nick")
            b.inputPassword.text.isNullOrEmpty() -> makeToast("Input your password")
            b.inputPassword.text.toString().length < 6 -> makeToast("Password have to be 6 symbols at least")
            b.repeatPassword.text.toString() != b.inputPassword.text.toString() -> makeToast("Incorrect password repeat")
            else -> return true
        }
        return false
    }

    private fun makeToast(m: String) {
        Toast.makeText(activity, m, Toast.LENGTH_SHORT).show()
    }
}