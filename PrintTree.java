package BinaryTree;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintTree {
    public static void main(String[] args) {
        BinarySearchTree tree1 = new BinarySearchTree();
        BinarySearchTree tree2 = new BinarySearchTree();
        Scanner sc = new Scanner(System.in);

        int choice = 0;

        while (choice != 5) {
            try {
            	System.out.println(MenuConstants.OPTION_1);
            	System.out.println(MenuConstants.OPTION_2);
            	System.out.println(MenuConstants.OPTION_3);
            	System.out.println(MenuConstants.OPTION_4);
            	System.out.println(MenuConstants.OPTION_5);
                System.out.print(MenuConstants.MENU_PROMPT);
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print(MenuConstants.ADD_TREE_PROMPT);
                        int size = sc.nextInt();
                        if (size <= 0) {
                            System.out.println(MenuConstants.SIZE_ERROR);
                            break;
                        }
                        int[] values = new int[size];
                        for (int i = 0; i < size; i++) {
                            System.out.print(MenuConstants.VALUE_PROMPT + (i + 1) + ": ");
                            values[i] = sc.nextInt();
                            if (values[i] < 1 || values[i] > 999) {
                                System.out.println(MenuConstants.VALUE_ERROR);
                                i--; // Prompt again for this index
                            }
                        }
                        System.out.print(MenuConstants.TREE_CHOICE_PROMPT);
                        int treeChoice = sc.nextInt();
                        if (treeChoice == 1) {
                            tree1.createIndex(values);
                            System.out.println("Tree 1:");
                            System.out.println(tree1.toString());
                        } else if (treeChoice == 2) {
                            tree2.createIndex(values);
                            System.out.println("Tree 2:");
                            System.out.println(tree2.toString());
                        } else {
                            System.out.println(MenuConstants.TREE_CHOICE_ERROR);
                        }
                        break;

                    case 2:
                        System.out.println(MenuConstants.TREE_EQUALITY_CHECK);
                        boolean areEqual = tree1.equals(tree2);
                        System.out.println(MenuConstants.TREE_EQUALITY_RESULT + areEqual);
                        break;

                    case 3:
                        System.out.print(MenuConstants.REMOVE_PROMPT);
                        int removeValue = sc.nextInt();
                        System.out.print(MenuConstants.TREE_CHOICE_PROMPT);
                        treeChoice = sc.nextInt();
                        if (treeChoice == 1) {
                            if (tree1.removeNode(removeValue)) {
                                System.out.println(MenuConstants.REMOVE_SUCCESS + removeValue + " from Tree 1");
                            } else {
                                System.out.println(MenuConstants.REMOVE_ERROR + "Tree 1.");
                            }
                            System.out.println(tree1.toString());
                        } else if (treeChoice == 2) {
                            if (tree2.removeNode(removeValue)) {
                                System.out.println(MenuConstants.REMOVE_SUCCESS + removeValue + " from Tree 2");
                            } else {
                                System.out.println(MenuConstants.REMOVE_ERROR + "Tree 2.");
                            }
                            System.out.println(tree2.toString());
                        } else {
                            System.out.println(MenuConstants.TREE_CHOICE_ERROR);
                        }
                        break;

                    case 4:
                        System.out.print(MenuConstants.ADD_TO_CHILDREN_PROMPT);
                        int addValue = sc.nextInt();
                        System.out.print(MenuConstants.TREE_CHOICE_PROMPT);
                        int treeChoice1 = sc.nextInt();
                        if (treeChoice1 == 1) {
                            tree1.addToChildren(addValue);
                            System.out.println(MenuConstants.MODIFIED_TREE_1);
                            System.out.println(tree1.toString());
                        } else if (treeChoice1 == 2) {
                            tree2.addToChildren(addValue);
                            System.out.println(MenuConstants.MODIFIED_TREE_2);
                            System.out.println(tree2.toString());
                        } else {
                            System.out.println(MenuConstants.TREE_CHOICE_ERROR);
                        }
                        break;

                    case 5:
                        System.out.println(MenuConstants.EXIT_MESSAGE);
                        break;

                    default:
                        System.out.println(MenuConstants.INVALID_CHOICE);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(MenuConstants.INVALID_INPUT);
                sc.next();
            }
        }

        sc.close();
    }
}
