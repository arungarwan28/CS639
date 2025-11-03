package dev.pace.businesscard.ui.theme

import androidx.compose.ui.text.googlefonts.GoogleFont
import dev.pace.businesscard.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val PoppinsFont = GoogleFont("Poppins")
