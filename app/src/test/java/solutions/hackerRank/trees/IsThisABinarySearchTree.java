package solutions.hackerRank.trees;

import com.escmobile.lab.domain.model.Node;

/**
 * Tree Challenges
 * https://www.hackerrank.com/interview/interview-preparation-kit/trees/challenges
 * Java implementation
 */
public class IsThisABinarySearchTree {

    boolean checkBST(Node root) {
        return checkBSTWithLimits(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean checkBSTWithLimits(Node node, int min, int max) {

        if (node == null) return true;

        if (node.getData() < min || node.getData() > max) return false;

        boolean isLeftValid = checkBSTWithLimits(node.getLeft(), min, node.getData() - 1);
        boolean isRightValid = checkBSTWithLimits(node.getRight(), node.getData(), max);

        return isLeftValid && isRightValid;
    }
}
