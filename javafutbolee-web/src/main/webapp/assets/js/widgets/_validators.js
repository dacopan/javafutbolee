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

function isValidCI(ced) {
    var isNumeric;
    var total = 0;
    var tamanoLongitudCedula = 10;
    var coeficientes = [2, 1, 2, 1, 2, 1, 2, 1, 2];
    var numeroProvincias = 24;
    var tercerDigito = 6;

    if (ced.length == tamanoLongitudCedula) {
        var provincia = parseInt(ced[0] + "" + ced[1]);
        var digitoTres = parseInt(ced[2] + "");
        if ((provincia > 0 && provincia <= numeroProvincias) && digitoTres < tercerDigito) {
            var digitoVerificadorRecibido = parseInt(ced[9] + "");
            for (var k = 0; k < coeficientes.length; k++) {
                var valor = parseInt(coeficientes[k] + "") *
                        parseInt(ced[k] + "");
                total = valor >= 10 ? total + (valor - 9) : total + valor;

            }
            var digitoVerificadorObtenido = total >= 10 ? (total % 10) != 0 ?
                    10 - (total % 10) : (total % 10) : total;
            return digitoVerificadorObtenido == digitoVerificadorRecibido;

        }
        return false;

    } else {
        return false;
    }
}
/**
 * Faces Validator
 */
PrimeFaces.validator['custom.cedulaValidator'] = {
    validate: function (element, value) {
        //use element.data() to access validation metadata, in this case there is none.
        if (!isValidCI(value)) {
            throw {
                summary: 'Validation Error',
                detail: value + ' no es una CI válida.'
            };
        }
    }
};