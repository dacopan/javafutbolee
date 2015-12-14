/*
 * Copyright (C) 2015 dacopan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.dacopancm.jfee.managedController;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author dacopan
 */
//@ManagedBean(name = "helperBean")
//@SessionScoped
public class HelperBean implements Serializable {

    /**
     * valida cedual de identidad ecuatoriana 2015
     *
     * @param ci cedula a validar
     * @return true si es cedula valida
     */
    public static boolean isValidCI(String ci) {
        //*
        char[] ced = ci.toCharArray();
        int isNumeric;
        int total = 0;
        int tamanoLongitudCedula = 10;
        int[] coeficientes = new int[]{2, 1, 2, 1, 2, 1, 2, 1, 2};
        int numeroProvincias = 24;
        int tercerDigito = 6;

        if (ced.length == tamanoLongitudCedula) {
            int provincia = Integer.parseInt(ced[0] + "" + ced[1]);
            int digitoTres = Integer.parseInt(ced[2] + "");
            if ((provincia > 0 && provincia <= numeroProvincias) && digitoTres < tercerDigito) {
                int digitoVerificadorRecibido = Integer.parseInt(ced[9] + "");
                for (int k = 0; k < coeficientes.length; k++) {
                    int valor = Integer.parseInt(coeficientes[k] + "")
                            * Integer.parseInt(ced[k] + "");
                    total = valor >= 10 ? total + (valor - 9) : total + valor;

                }
                int digitoVerificadorObtenido = total >= 10 ? (total % 10) != 0
                        ? 10 - (total % 10) : (total % 10) : total;
                return digitoVerificadorObtenido == digitoVerificadorRecibido;

            }
            return false;

        } else {
            return false;
        }
        //*
    }
   
}
