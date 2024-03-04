class TrieNode {
    private final TrieNode[] children = new TrieNode[52];
    private boolean isEndOfWord;

    private int getIndex(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A';
        } else {
            return ch - 'a' + 26;
        }
    }

    public boolean containsKey(char ch) {
        return children[getIndex(ch)] != null;
    }

    public TrieNode get(char ch) {
        return children[getIndex(ch)];
    }

    public void put(char ch, TrieNode node) {
        children[getIndex(ch)] = node;
    }

    public void setEnd() {
        isEndOfWord = true;
    }

    public boolean isEnd() {
        return isEndOfWord;
    }
}
