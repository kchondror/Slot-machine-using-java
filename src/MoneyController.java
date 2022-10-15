public class MoneyController {
    User player;

    public MoneyController(User player){
        this.player=player;

    }

    public int DetermineMultiples(Spin.Symbols symbol){

            if(symbol== Spin.Symbols.cherries)
                return 2;
            else if (symbol== Spin.Symbols.club)
                return 3;
            else if (symbol== Spin.Symbols.diamond)
                return 100;
            else if (symbol== Spin.Symbols.heart)
                return 4;
            else if (symbol== Spin.Symbols.joker)
                return 40;
            else if (symbol== Spin.Symbols.seven)
                return 5;
            else if (symbol== Spin.Symbols.spade)
                return 6;

        return 0;
    }

    public Boolean isAWin(Spin spin){
        if(spin.getSymbol1().equals(spin.getSymbol2()) && spin.getSymbol1().equals(spin.getSymbol3())
         || spin.getSymbol2().equals(Spin.Symbols.joker) && spin.getSymbol1().equals(spin.getSymbol3())
         || spin.getSymbol3().equals(Spin.Symbols.joker) && spin.getSymbol2().equals(spin.getSymbol1())
         || spin.getSymbol2().equals(Spin.Symbols.joker) && spin.getSymbol3().equals(Spin.Symbols.joker) ) {

            Update(spin,spin.getSymbol1());
            return true;

        }else if(spin.getSymbol1().equals(Spin.Symbols.joker) && spin.getSymbol2().equals(spin.getSymbol3())
         || spin.getSymbol1().equals(Spin.Symbols.joker) && spin.getSymbol3().equals(Spin.Symbols.joker) ){

            Update(spin,spin.getSymbol2());
            return true;

        }else if(spin.getSymbol1().equals(Spin.Symbols.joker) && spin.getSymbol2().equals(Spin.Symbols.joker) ){

            Update(spin,spin.getSymbol3());
            return true;
        }
        else
            spin.IncreaseLosses();

        return false;

    }

    public void Update(Spin spin,Spin.Symbols symbol){
        spin.setLosses(0);
        player.UpdateBalance(true, DetermineMultiples(symbol));
    }

}
