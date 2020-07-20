package smet2133.arithmetic_basic.persistance

import smet2133.arithmetic_basic.Arithmetic
import smet2133.arithmetic_basic.logic.Test
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.ObjectInputStream

class Util {

    companion object {
        fun loadReports(): MutableList<Test>? {
            return try {
                val fis: FileInputStream = Arithmetic.getAppContext().openFileInput("reports")
                val objectInputStream = ObjectInputStream(fis)
                val tests: MutableList<Test> = objectInputStream.readObject() as MutableList<Test>
                objectInputStream.close()
                fis.close()
                tests
            } catch (e: FileNotFoundException) {
                null
            }

        }
    }


}