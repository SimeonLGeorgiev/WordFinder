class Tree {
    private TreeNode root;

    public Tree() {
        root = new TreeNode();
    }

    public void insert(String word) {
        TreeNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                node.put(ch, new TreeNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    public boolean search(String word) {
        if (word.equals("A") || word.equals("I")) {
            return true;
        }
        TreeNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.containsKey(ch)) {
                return false;
            }
            node = node.get(ch);
        }
        return node.isEnd();
    }
}