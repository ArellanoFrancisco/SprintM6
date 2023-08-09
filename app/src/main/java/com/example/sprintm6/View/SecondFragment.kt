package com.example.sprintm6.View


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.sprintm6.R
import com.example.sprintm6.ViewModel.PhoneViewModel
import com.example.sprintm6.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    lateinit var binding: FragmentSecondBinding
    private val viewModel: PhoneViewModel by activityViewModels()
    private var phoneid: Int = 0
    private var phoneName: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondBinding.inflate(inflater, container, false)
        arguments?.let { bundle ->
            phoneid = bundle.getInt("PhoneId")
            phoneName = bundle.getString("phoneName").toString()
            Log.d("seleccion2", "Bundle recibido: phoneid=$phoneid, phoneName=$phoneName")
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        phoneid.let { id ->
            viewModel.getPhoneDetailsByIdFromInternet(id)
        }

        viewModel.getPhoneDetail().observe(viewLifecycleOwner, Observer {
            Log.d("enviar correo", phoneid.toString())
            binding.nameTitle.text = it.name
            Glide.with(binding.imagePhone).load(it.image).into(binding.imagePhone)
            binding.Antes.text = binding.root.context.getString(R.string.Antes, it.lastPrice)
            binding.Ahora.text = binding.root.context.getString(R.string.Ahora, it.price)
            binding.Credito.text = if (it.credit == true) {
                binding.root.context.getString(R.string.True)
            } else {
                binding.root.context.getString(R.string.False)
            }
            binding.Description.text =
                binding.root.context.getString(R.string.Description, it.description)

            binding.Contactar.setOnClickListener {
                Log.d("Button", "Contactar")
                enviarCorreo(phoneid, phoneName)
            }
        })
    }

    /*    fun obtenerdatos(): Pair<Int, String> {
            arguments?.let { bundle ->
                phoneid = bundle.getInt("PhoneId")
                phoneName = bundle.getString("phoneName").toString()
                Log.d("seleccion2", "Bundle recibido: phoneid=$phoneid, phoneName=$phoneName")
            }
            return Pair(phoneid, phoneName)
        }*/
    fun enviarCorreo(id: Int, name: String) {
        Log.d("fun", "funcion enviar correo")
        val recipientEmail = "info@novaera.cl"
        val subject = "Consulta $name id $id"
        val message = "Hola,\n\n" +
                "Vi la propiedad $name de código $id y me gustaría que me contactaran a este correo o al siguiente número:\n" +
                "_________\n" + "Quedo atento."

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipientEmail))
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(intent, "Enviar correo"))
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error al enviar el correo", Toast.LENGTH_SHORT)
                .show()
        }
    }
}



