package com.osgl.sample;

import org.osgl.util.C;
import org.osgl.util.IO;
import org.osgl.util._;

import java.util.List;

public class MappingSample {
    public static void main(String[] args) {
        List<Integer> l = C.list(1, 2, 3);
        C.lc(l).map(_.f.dbl(), IO.f.PRINTLN).walkthrough();
    }
}