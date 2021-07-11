package com.hakjae.lotto.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hakjae.lotto.R
import com.hakjae.lotto.databinding.ActivityReaderBinding
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class ReaderActivity : AppCompatActivity(), DecoratedBarcodeView.TorchListener {

    private lateinit var binding: ActivityReaderBinding
    private lateinit var manager: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reader)

        manager = CaptureManager(this, binding.barcodeView)
        manager.initializeFromIntent(intent, savedInstanceState)
        manager.decode()

        var flashStatus = false

        binding.flashLight.setOnClickListener {
            flashStatus = if (flashStatus) {
                binding.barcodeView.setTorchOff()
                binding.flashLight.setImageResource(R.drawable.ic_baseline_flash_off_24dp)
                false
            } else {
                binding.barcodeView.setTorchOn()
                binding.flashLight.setImageResource(R.drawable.ic_baseline_flash_on_24dp)
                true
            }
        }
    }

    override fun onTorchOn() {
    }

    override fun onTorchOff() {
    }

    override fun onResume() {
        super.onResume()
        manager.onResume()
    }

    override fun onPause() {
        super.onPause()
        manager.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        manager.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        manager.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}