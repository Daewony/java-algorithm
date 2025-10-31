import java.util.*;
import java.io.*;


public class BOJ_7576 {
    static int M, N;
    static int[][] box;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static Queue<Point> q = new LinkedList<>();

    static class Point {
        int y, x, day;

        Point(int y, int x, int day) {
            this.y = y;
            this.x = x;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        box = new int[N][M];
        int unripeCount = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j] == 1) {
                    q.offer(new Point(i,j,0));
                } else if (box[i][j] == 0) {
                    unripeCount++;
                }
            }
        }

        if(unripeCount == 0) {
            System.out.println(0);
            return;
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        int maxDay = 0;

        while(!q.isEmpty()) {
            Point c = q.poll();
            maxDay = c.day;

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int ny = c.y + dy[i];
                int nx = c.x + dx[i];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    if (box[ny][nx] == 0) {
                        box[ny][nx] = 1; // 토마토를 익게 하고 (방문 처리와 같은 효과)
                        q.offer(new Point(ny, nx, c.day + 1)); // 큐에 추가
                    }
                }
            }
        }

        // BFS가 끝난 후, 여전히 익지 않은 토마토가 있는지 확인
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (box[i][j] == 0) {
                    return -1; // 하나라도 0이 남아있으면 -1 반환
                }
            }
        }

        return maxDay;
    }
}