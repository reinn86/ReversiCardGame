package domain.service

import java.awt.Desktop
import java.net.URI

interface OAuthCertifiable {
    fun authenticateGoogle() {
        val siteURL = "https://accounts.google.com/o/oauth2/auth?response_type=code"
        val clientId = "232804140396-l1uctpa0d9goikf3k4dft39c6imklns9.apps.googleusercontent.com"
        val redirectURI = "https://www.google.co.jp/"
        val scope  = "email"
        val accessType = "offline&approval_prompt=force"

        val url = "${siteURL}" +
                "&client_id=${clientId}" +
                "&redirect_uri=${redirectURI}" +
                "&scope=${scope}" +
                "&access_type=${accessType}"

        val desktop = Desktop.getDesktop()
        println(System.getProperties())
        desktop.browse(URI(url))
    }

    fun getUserInfo() {

    }
}