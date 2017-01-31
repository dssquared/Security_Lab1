public class Card {
    private int track = 0;
    private String raw;
    private String formatCode;
    private String pan;
    private String name;
    private String yymm; // Expire Date
    private String sc; // Service Code
    private String dd; // Discretionary Data

    /**
     * Constructor for unrecognized format
     * @param  all regex match result
     */
    public Card(String raw){
        this.raw = raw;
    }

    /**
     * Constructor for track 1 and 2 cards
     * @param  track      int for track 1 or 2
     * @param  raw        the raw data for debug
     * @param  formatCode single character for format on track 1, left null if track 2
     * @param  pan        primary account number
     * @param  name       name of card holder, if track 2 left null
     * @param  yymm       Expiration Date of card in YYMM format
     * @param  sc         CVC number
     * @param  dd         Discretionary Data
     */
    public Card(int track, String raw, String formatCode, String pan, String name, String yymm, String sc, String dd){
        this.track = track;
        this.raw = raw;
        this.formatCode = formatCode;
        this.pan = pan;
        this.name = name;
        this.yymm = yymm;
        this.sc = sc;
        this.dd = dd;
    }

    /**
     * Prints out the data of the card. Different format of output for each track
     */
    public void print(){
        // System.out.println("Raw:\n"+this.raw);// DEBUG
        switch(this.track){
            case 1:
                System.out.printf("Track: %d%nFormat Code: %s%nCardholder's Name: %s%nCard Number: %s%nExpiration Date: %s/%s%nCVC Number: %s%nDiscretionary data: %s%n%n", this.track, this.formatCode, this.name, this.pan, this.yymm.substring(2), this.yymm.substring(0, 2), this.sc, this.dd);
                return;
            case 2:
                System.out.printf("Track: %d%nCard Number: %s%nExpiration Date: %s/%s%nCVC Number: %s%nDiscretionary data: %s%n%n", this.track, this.pan, this.yymm.substring(2), this.yymm.substring(0, 2), this.sc, this.dd);
                return;
            default:
                System.out.printf("Card Format not recognized%n%s%n%n", this.raw);
        }
    }
}
