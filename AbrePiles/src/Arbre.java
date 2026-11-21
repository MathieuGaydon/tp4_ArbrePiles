import java.util.ArrayList;

public class Arbre {

    public static class BinaryTree<T> {

        private static class Node<E> {
            private final E element;
            private final Node<E> left;
            private final Node<E> right;

            public Node(E element, Node<E> left, Node<E> right) {
                this.element = element;
                this.left = left;
                this.right = right;
            }

            public E getElement() {
                return element;
            }
            public Node<E> getLeft() {
                return left;
            }
            public Node<E> getRight() {
                return right;
            }
        }

        private final Node<T> root;
        public BinaryTree() {
            root = null;
        }
        public BinaryTree(T element) {
            root = new Node<>(element, null, null);
        }

        public BinaryTree(T element, BinaryTree<T> left, BinaryTree<T> right) {
            Node<T> leftNode = null;
            Node<T> rightNode = null;
            if (left != null) {
                leftNode = left.root;
            }
            if (right != null) {
                rightNode = right.root;
            }
            root = new Node<>(element, leftNode, rightNode);
        }

        public boolean isEmpty() {
            return root == null;
        }

        private void prefix(Node<T> node, StringBuilder result) {
            if (node == null) {
                return;
            }
            result.append(node.getElement()).append(" ");
            prefix(node.getLeft(), result);
            prefix(node.getRight(), result);
        }

        public void prefix() {
            System.out.println(toString());
        }

        public String toString() {
            StringBuilder result = new StringBuilder();
            prefix(root, result);
            return result.toString().trim();
        }
    }

    public static class NaryTree<T> {

        private static class Node<E> {
            private final E element;
            private final ArrayList<Node<E>> children;

            public Node(E element) {
                this.element = element;
                this.children = new ArrayList<>();
            }

            public E getElement() {
                return element;
            }
            public ArrayList<Node<E>> getChildren() {
                return children;
            }
        }

        private final Node<T> root;
        public NaryTree(T element) {
            root = new Node<>(element);
        }
        public void addChild(NaryTree<T> child) {
            root.getChildren().add(child.root);
        }

        private void prefix(Node<T> node, int depth, StringBuilder result) {
            for (int i = 0; i < depth; i++) {
                result.append("  ");
            }
            result.append(node.getElement()).append("\n");
            for (Node<T> child : node.getChildren()) {
                prefix(child, depth + 1, result);
            }
        }

        public String toString() {
            StringBuilder result = new StringBuilder();
            prefix(root, 0, result);
            return result.toString();
        }
    }

    public static void main(String[] args) {

        BinaryTree<String> cinq = new BinaryTree<>("5");
        BinaryTree<String> deux = new BinaryTree<>("2");
        BinaryTree<String> huit = new BinaryTree<>("8");
        BinaryTree<String> plus = new BinaryTree<>("+", cinq, deux);
        BinaryTree<String> fois = new BinaryTree<>("*", plus, huit);

        System.out.println("Expression (5 + 2) * 8 en préfixé :");
        System.out.println(fois);

        NaryTree<String> html = new NaryTree<>("html");

        NaryTree<String> head = new NaryTree<>("head");
        NaryTree<String> title = new NaryTree<>("title");
        title.addChild(new NaryTree<>("Page test"));
        head.addChild(title);

        NaryTree<String> body = new NaryTree<>("body");
        NaryTree<String> h1 = new NaryTree<>("h1");
        h1.addChild(new NaryTree<>("Titre niveau 1"));
        NaryTree<String> p = new NaryTree<>("p");
        p.addChild(new NaryTree<>("Ceci est un paragraphe"));
        body.addChild(h1);
        body.addChild(p);

        html.addChild(head);
        html.addChild(body);

        System.out.println();
        System.out.println("DOM HTML :");
        System.out.println(html);
    }
}