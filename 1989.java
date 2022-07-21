import java.util.*;
import java.io.*;

public class Main {
    static int[] uf;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<int[]> ka=new ArrayList<>();

        int a=Integer.parseInt(bf.readLine());
        String[] s=bf.readLine().split(" ");
        for(int i=0; i<a; i++) ka.add(new int[]{Integer.parseInt(s[i]),i});
        ka.sort((o1, o2) -> o2[0]-o1[0]);
        long[] k=new long[a];
        uf=new int[a];
        for(int i=0; i<a; i++) uf[i]=i;
        long max=0;
        for(int i=0; i<a; i++){
            int[] p=ka.get(i);
            k[p[1]]=p[0];
            if(p[1]-1>=0 && k[find(p[1]-1)]!=0){
                k[p[1]]+=k[find(p[1]-1)];
                union(p[1],find(p[1]-1));
            }
            if(p[1]+1<a && k[find(p[1]+1)]!=0){
                k[p[1]]+=k[find(p[1]+1)];
                union(p[1],find(p[1]+1));
            }
            max=Math.max(p[0]*k[p[1]],max);
        }
        System.out.println(max);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;
        uf[y] = x;
    }

    static int find(int x) {
        if (uf[x] == x) return x;
        else return uf[x] = find(uf[x]);
    }
}
