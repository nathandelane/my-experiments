package com.github.nathandelane.experiments.search;

import java.util.Locale;

public class RuleDataTaxonomyAddressStructure {

  public static class NumericPostalCodeIndex {

    private final AddressSearchTree[] addressSearchTreeIndex;

    public NumericPostalCodeIndex() {
      addressSearchTreeIndex = new AddressSearchTree[10]; // Because each postal code starts with a digit
    }

    public void setAddressSearchTree(final AddressSearchTree addressSearchTree) {
      this.addressSearchTreeIndex[addressSearchTree.getIndex()] = addressSearchTree;
    }

  }

  public static class AddressSearchTree {

    private AddressTreeNode root;

    public AddressSearchTree() {
      root = null;
    }

    public AddressSearchTree(final AddressTreeNode root) {
      this.root = root;
    }

    public void add(final AddressTreeNode node) {
      if (root == null) {
        root = node;
      }
      else {
        if (root.compareTo(node) == 0 && !root.contains(node)) {
          root = root.append(node);
        }
        else {
          // Insert node ini the proper in-order-location in the tree
        }
      }
    }

    public Integer getIndex() {
      final Integer index;

      if (root == null) {
        return null;
      }
      else {
        final String postalCode = root.postalCodeLow;
        final String firstInt = "" + postalCode.charAt(0);

        index = Integer.parseInt(firstInt);
      }

      return index;
    }

    public static class AddressTreeNode implements Comparable<AddressTreeNode> {

      private final String postalCodeLow;

      private final String postalCodeHigh;

      private final String stateRegionCode;

      private AddressTreeNode parent;

      private AddressTreeNode left;

      private AddressTreeNode right;

      public AddressTreeNode(final String postalCodeLow, final String postalCodeHigh, final String stateRegionCode) {
        this.postalCodeLow = postalCodeLow;
        this.postalCodeHigh = postalCodeHigh;
        this.stateRegionCode = stateRegionCode;
      }

      public boolean containsPostalCode(final String postalCode) {
        return postalCodeLow.compareTo(postalCode) >= 0 && postalCodeHigh.compareTo(postalCode) <= 0;
      }

      public boolean matchesStateRegion(final String otherStateRegionCode) {
        return this.stateRegionCode != null && stateRegionCode.equals(otherStateRegionCode.trim().toLowerCase(Locale.ROOT));
      }

      public boolean contains(final AddressTreeNode other) {
        return containsPostalCode(other.postalCodeLow) && containsPostalCode(other.postalCodeHigh);
      }

      public boolean overlaps(final AddressTreeNode other) {
        return containsPostalCode(other.postalCodeLow) || containsPostalCode(postalCodeHigh);
      }

      public AddressTreeNode append(final AddressTreeNode other) {
        final AddressTreeNode newNode;

        if (overlaps(other)) {
          if (containsPostalCode(other.postalCodeLow)) {
            newNode = new AddressTreeNode(this.postalCodeLow, other.postalCodeHigh, this.stateRegionCode);
          }
          else {
            newNode = new AddressTreeNode(other.postalCodeLow, this.postalCodeHigh, this.stateRegionCode);
          }
        }
        else {
          newNode = this;
        }

        return newNode;
      }

      public AddressTreeNode getParent() {
        return parent;
      }

      public void setParent(final AddressTreeNode newParent) {
        this.parent = newParent;
      }

      public AddressTreeNode getLeft() {
        return left;
      }

      public void setLeft(final AddressTreeNode newLeft) {
        this.left = newLeft;
      }

      public AddressTreeNode getRight() {
        return right;
      }

      public void setRight(final AddressTreeNode newRight) {
        this.right = newRight;
      }

      @Override
      public int compareTo(final AddressTreeNode other) {
        if (this.postalCodeHigh.compareTo(other.postalCodeLow) < 0) {
          return - 1;
        }
        else if (this.postalCodeLow.compareTo(other.postalCodeHigh) > 0) {
          return 1;
        }
        else {
          return 0;
        }
      }
    }

  }

}
