package com.github.alexpfx.udacity.beercollection.utils;

import java.util.ArrayList;
import java.util.List;

public class FilterUtils {


    /**
     * Método utilitário utilizado para realizar queries de propósito geral sobre os items de uma lista.
     *
     * @param inputList     Lista de entrada
     * @param predicate     Usado para filtrar os itens.
     * @param function      Recebe uma função que pode ser usada para aplicar uma transformação em cada item
     *                      retornado pelo filtro
     * @param stopCondition se a condição for satisfeita, força a parada do laço, returnando a lista criada até então.
     * @param <RT>          Tipo de retorno da função
     * @param <P>
     * @return
     */
    public static <RT, P> List<RT> filter(List<P> inputList, Predicate<P>
            predicate, Function<P, RT> function,
                                          Predicate<P> stopCondition) {

        List<RT> list = new ArrayList<>();
        for (P item : inputList) {
            if (predicate.test(item)) {
                list.add(function.apply(item));
            }
            if (stopCondition != null && stopCondition.test(item)) {
                return list;
            }
        }
        return list;
    }
}
