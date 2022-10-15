import java.util.ArrayList;
import java.util.Random;

public class Spin {

    private Symbols Symbol1;
    private Symbols Symbol2;
    private Symbols Symbol3;
    private final ArrayList<Integer> possibleNums;
    private int losses;
    private final Random random;

    private final int[] arr ={0,1,2,3,4,5,6};
    private final int[] freq ={25,25,6,13,8,12,10};

    public Spin(){
        SetSymbol1(null);
        SetSymbol2(null);
        SetSymbol3(null);
        possibleNums=new ArrayList<>();
        random=new Random();
        
    }

    enum Symbols {
        cherries,
        club,
        diamond,
        heart,
        joker,
        seven,
        spade
    }


    public Symbols DetermineSymbol(){

        Symbols currentSymbol=Symbols.seven;

        int randPick=possibleNums.get(random.nextInt(possibleNums.size()));

        switch (randPick) {
            case 0 :
                currentSymbol=Symbols.cherries;
                break;
            case 1 :
                currentSymbol=Symbols.club;
                break;
            case 2 :
                currentSymbol=Symbols.diamond;
                break;
            case 3 :
                currentSymbol=Symbols.heart;
                break;
            case 4 :
                currentSymbol=Symbols.joker;
                break;
            case 5 :
                currentSymbol=Symbols.seven;
                break;
            case 6 :
                currentSymbol=Symbols.spade;

        }

        return currentSymbol;

    }

    public void LuckDesider(){

        for (int i=0;i<100;i++){
            possibleNums.add(myRand());
        }
        SetAllSymbols();

    }

            private int findCeil(int[] prefix,int r){
                int mid;
                int l=0,h=6;
                while (l < h)
                {
                    mid = l + ((h - l) >> 1);
                    if(r > prefix[mid])
                        l = mid + 1;
                    else
                        h = mid;
                }
                return (prefix[l] >= r) ? l : -1;
            }


            public int myRand(){
                int[] prefix = new int[7];
                prefix[0] = freq[0];
                for (int i = 1; i < 7; ++i)
                    prefix[i] = prefix[i - 1] + freq[i];


                int r = ((int)(Math.random()*(323567)) % prefix[7 - 1]) + 1;

                int indexc = findCeil(prefix,r);
                return arr[indexc];
            }




    public void SetAllSymbols(){
        Symbol1=DetermineSymbol();
        Symbol2=DetermineSymbol();
        Symbol3=DetermineSymbol();
    }


    public void SetSymbol1(Symbols symbol){
        Symbol1=symbol;
    }
    public void SetSymbol2(Symbols symbol){
        Symbol2=symbol;
    }
    public void SetSymbol3(Symbols symbol){
        Symbol3=symbol;
    }


    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void IncreaseLosses() {
        losses++;
    }

    public Symbols getSymbol1(){
        return Symbol1;
    }

    public Symbols getSymbol2(){
        return Symbol2;
    }

    public Symbols getSymbol3(){
        return Symbol3;
    }

}
