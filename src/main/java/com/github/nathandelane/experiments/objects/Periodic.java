package com.github.nathandelane.experiments.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Periodic {

  public static void main(String[] args) {
    Set<PeriodicElement> elements = addAll();
    System.out.println("##### UNSORTED #####");
    elements.forEach(element -> {
      System.out.println(element);
    });

    List<PeriodicElement> listElements = new ArrayList<PeriodicElement>(elements);
    Collections.sort(listElements);
    System.out.println("#####  SORTED  #####");
    listElements.forEach(element -> {
      System.out.println(element);
    });
  }

  private static Set<PeriodicElement> addAll() {
    // A LinkedHashSet must be used to ensure insertion order
    Set<PeriodicElement> elements = new LinkedHashSet<PeriodicElement>();
    elements.add(new PeriodicElement.Builder().withAtomicNumber(1).withName("Hydrogen").withSymbol("H")
        .withAtomicMass(1.007).build());
    elements.add(new PeriodicElement.Builder().withAtomicNumber(1).withName("Hydrogen").withSymbol("H")
      .withAtomicMass(1.007).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(2).withName("Helium").withSymbol("He")
//        .withAtomicMass(4.002).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(3).withName("Lithium").withSymbol("Li")
//        .withAtomicMass(6.941).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(4).withName("Beryllium").withSymbol("Be")
//        .withAtomicMass(9.012).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(5).withName("Boron").withSymbol("B")
//        .withAtomicMass(10.811).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(6).withName("Carbon").withSymbol("C")
//        .withAtomicMass(12.011).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(7).withName("Nitrogen").withSymbol("N")
//        .withAtomicMass(14.007).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(8).withName("Oxygen").withSymbol("O")
//        .withAtomicMass(15.999).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(9).withName("Fluorine").withSymbol("F")
//        .withAtomicMass(18.998).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(10).withName("Neon").withSymbol("Ne")
//        .withAtomicMass(20.18).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(11).withName("Sodium").withSymbol("Na")
//        .withAtomicMass(22.99).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(12).withName("Magnesium").withSymbol("Mg")
//        .withAtomicMass(24.305).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(13).withName("Aluminum").withSymbol("Al")
//        .withAtomicMass(26.982).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(14).withName("Silicon").withSymbol("Si")
//        .withAtomicMass(28.086).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(15).withName("Phosphorus").withSymbol("P")
//        .withAtomicMass(30.974).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(16).withName("Sulfur").withSymbol("S")
//        .withAtomicMass(32.065).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(17).withName("Chlorine").withSymbol("Cl")
//        .withAtomicMass(35.453).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(18).withName("Argon").withSymbol("Ar")
//        .withAtomicMass(39.948).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(19).withName("Potassium").withSymbol("K")
//        .withAtomicMass(39.098).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(20).withName("Calcium").withSymbol("Ca")
//        .withAtomicMass(40.078).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(21).withName("Scandium").withSymbol("Sc")
//        .withAtomicMass(44.956).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(22).withName("Titanium").withSymbol("Ti")
//        .withAtomicMass(47.867).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(23).withName("Vanadium").withSymbol("V")
//        .withAtomicMass(50.942).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(24).withName("Chromium").withSymbol("Cr")
//        .withAtomicMass(51.996).build());
//    elements.add(new PeriodicElement.Builder().withAtomicNumber(25).withName("Manganese").withSymbol("Mn")
//        .withAtomicMass(54.938).build());
    return elements;
  }
}
