package com.pvapp.PVApp.Utils;

import java.util.Set;

public class IdGenerator {
    private static int generateId(Set<Integer> exisitingIds) {
        if (exisitingIds.isEmpty()) {
            return 0;
        } else {
            int id = exisitingIds.stream().max((o1, o2) -> o1.compareTo(o2)).get();
            return id + 1;
        }

    }
}
