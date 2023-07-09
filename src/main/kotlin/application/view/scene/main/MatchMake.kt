package application.view.scene.main

import application.view.Panel
import java.awt.Dimension
import javax.swing.JLabel

class MatchMake : Panel(){


    //components
    private val matchingTextLabel = JLabel()

    init {
        //mattingTextLabel
        matchingTextLabel.size = Dimension(vw(80.0),vh(10.0))
        matchingTextLabel.text = "マッチング中..."
        matchingTextLabel.setLocation(vw(10.0),vh(70.0))

        add(matchingTextLabel)
    }
}