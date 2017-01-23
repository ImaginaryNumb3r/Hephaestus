package core.util;

/**
 * @author Patrick
 * @created 29.06.2016
 */
public class Strings {

    // Private Constructor
    private Strings(){ }

    public static Strings getInstance(){
        return StringServiceInstance._instance;
    }

    /**
     * Removes the first occurrence from the base of the string
     * @param base The string that has parts to be removed
     * @param remove The pattern which is deleted from the base
     * @return New String with removed parts
     */
    public String remove(String base, String remove){
        StringBuilder builder = new StringBuilder(base);
        boolean removed = false;

        for (int i = 0, shift = 0; i != base.length() && !removed; ++i){

            // Check for end
            if (shift == remove.length()){
                removed = true;

            } else {
                if (base.charAt(i) == remove.charAt(shift)){
                    builder.deleteCharAt(i - shift);
                    ++shift;
                } else {
                    // reset variables
                    shift = 0;
                    builder = new StringBuilder(base);
                }
            }
        }
        return builder.toString();
    }

    public String charMult(char ch,int mult){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mult; ++i){
            builder.append(ch);
        }

        return builder.toString();
    }

    public boolean caselessEquals(String str1, String str2){
        return str1.toLowerCase().equals(str2.toLowerCase());
    }

    public String copyString(String original){
        StringBuilder builder = new StringBuilder(original);

        for (byte ch : original.getBytes()){
            builder.append((char) ch);
        }

        return builder.toString();
    }

    private static class StringServiceInstance{
        private static Strings _instance = new Strings();
    }
}
