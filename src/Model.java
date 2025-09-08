import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Model {
    ArrayList<String> tokens;
    Map<String,Integer> total;
    Map<String, Next> model;

    public Model(){
        tokens = getTokens("dataset.txt");
        total = getTotal(tokens);
        model = getModel(tokens);
    }

    public ArrayList<String> getTokens(String filename) {
        ArrayList<String> t = new ArrayList<>();
        final List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String data = " ";

        for (int i = 0; i < lines.size(); i++) {
            data = lines.get(i);
            StringTokenizer token = new StringTokenizer(data);
            while (token.hasMoreElements()) {
                t.add(token.nextToken());
            }
            t.add(".");
        }
        return t;
    }

    public Map<String,Integer> getTotal(ArrayList<String> tok) {
        TreeMap<String,Integer> t = new TreeMap<>();
        int c;
        for(int i = 0; i< tok.size() ; i++){
            c = 1;
            if(t.containsKey(tok.get(i))){
                c = t.get(tok.get(i))+1;
            }
            t.put(tok.get(i), c);
        }
        return t;
    }

    public HashMap<String, Next> getModel(ArrayList<String> tok) {
        HashMap<String, Next> m = new HashMap<>();
        for(int i = 0; i< (tok.size()-1) ; i++){
            Next n;
            if(m.containsKey(tok.get(i))){
                n = m.get(tok.get(i));
                if(n.next.containsKey(tok.get(i+1))){
                    n.next.put(tok.get(i+1), n.next.get(tok.get(i+1))+1);
                }
                else{
                    n.next.put(tok.get(i+1),1);
                }
            }else{
                n = new Next(tok.get(i+1),1);
                m.put(tok.get(i),n);
            }
        }
        return m;
    }

    public String predictNextWord(String word){
        if(!model.containsKey(word) || total.isEmpty()){
            return ".";
        }
        TreeMap<String,Integer> tm = model.get(word).next;
        String val = tm.firstKey();
        int max = tm.get(val);
        Iterator<String> it = tm.keySet().iterator();

        ArrayList<String> al = new ArrayList<>();
        al.add(val);
        int flag = 0;

        while(it.hasNext()){
            String next = it.next();
            if(tm.get(next) > max){
                max = tm.get(next);
                val = next;

                al.add(next);

            } else if (tm.get(next) == max) {
                al.add(next);
                flag = 1;
            }
        }
        if (flag == 0) {
            return val;
        }else{
            int r = (int) (al.size() * Math.random());
            return al.get(r);
        }
    }

    public String generate(String word){
        StringBuffer sb = new StringBuffer();
        sb.append(word);
        while(!word.equals(".")){
            String nw = predictNextWord(word);
            sb.append(" ");
            sb.append(nw);
            word = nw;
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "----------WORDS----------\n" + model +
                "\n----------DATASET WORD COUNT----------\n" + total;
    }
}
