package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 리코쳇로봇 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] boardMap;
    static boolean[][] isVisited;
    static int row, col;
    static Node startNode, endNode;
    static int minResult = Integer.MAX_VALUE;

    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int solution(String[] board) {
        int answer = 0;

        row = board.length;
        col = board[0].length();
        boardMap = new int[row][col];
        isVisited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i].charAt(j) == '.') {
                    boardMap[i][j] = 0;
                } else if (board[i].charAt(j) == 'D') { //장애물
                    boardMap[i][j] = -1;
                } else if (board[i].charAt(j) == 'R') { //시작
                    boardMap[i][j] = 1;
                    startNode = new Node(i, j);
                } else { //도착
                    boardMap[i][j] = 2;
                    endNode = new Node(i, j);
                }
            }
        }

        bfs();
        if (minResult == Integer.MAX_VALUE) {
            answer = -1;
        } else {
            answer = minResult;
        }

        return answer;
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode.x);
        queue.add(startNode.y);
        queue.add(0);
        isVisited[startNode.x][startNode.y] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();
            int cnt = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (!isValid(newX, newY) || boardMap[newX][newY] == -1) { // 가능성 있는 애들만 남겨둠
                    continue;
                }

                Node newNode = move(newX, newY, i);
                if(isVisited[newNode.x][newNode.y]){
                    continue;
                }

                if (newNode.x == endNode.x && newNode.y == endNode.y) {
                    minResult = cnt + 1;
                    return;
                }

                queue.add(newNode.x);
                queue.add(newNode.y);
                queue.add(cnt + 1);
                isVisited[newNode.x][newNode.y] = true;
            }


        }
    }

    public static Node move(int x, int y, int direction) {
        Node returnNode = new Node(x, y);

        while (isValid(x, y) && (boardMap[x][y] != -1)) {
            returnNode.x = x;
            returnNode.y = y;

            x += dx[direction];
            y += dy[direction];

        }

        return returnNode;
    }


    public static boolean isValid(int x, int y) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return false;
        }
        return true;
    }

}
