class Solution {

    class Node {
        char leftChar;
        char rightChar;

        int length;
        int prefix;
        int suffix;
        int max;

        Node(char c) {
            leftChar = c;
            rightChar = c;
            length = 1;
            prefix = 1;
            suffix = 1;
            max = 1;
        }
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {

        arr = s.toCharArray();

        int n = arr.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queryIndices.length];

        for(int i = 0; i < queryIndices.length; i++) {

            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            ans[i] = tree[1].max;
        }

        return ans;
    }

    // Build Segment Tree
    void build(int node, int start, int end) {

        if(start == end) {
            tree[node] = new Node(arr[start]);
            return;
        }

        int mid = start + (end - start) / 2;

        build(node * 2, start, mid);

        build(node * 2 + 1, mid + 1, end);

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    // Update one character
    void update(
        int node,
        int start,
        int end,
        int index,
        char c
    ) {

        if(start == end) {

            tree[node] = new Node(c);

            return;
        }

        int mid = start + (end - start) / 2;

        if(index <= mid) {

            update(
                node * 2,
                start,
                mid,
                index,
                c
            );

        } else {

            update(
                node * 2 + 1,
                mid + 1,
                end,
                index,
                c
            );
        }

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    // Merge two nodes
    Node merge(Node left, Node right) {

        Node result = new Node(left.leftChar);

        result.length = left.length + right.length;

        result.leftChar = left.leftChar;
        result.rightChar = right.rightChar;

        result.prefix = left.prefix;
        result.suffix = right.suffix;

        result.max = Math.max(
            left.max,
            right.max
        );

        // Boundary characters same
        if(left.rightChar == right.leftChar) {

            result.max = Math.max(
                result.max,
                left.suffix + right.prefix
            );

            // Entire left part is same character
            if(left.prefix == left.length) {

                result.prefix =
                    left.length + right.prefix;
            }

            // Entire right part is same character
            if(right.suffix == right.length) {

                result.suffix =
                    left.suffix + right.length;
            }
        }

        return result;
    }
}