import java.io.IOException;
import java.util.Arrays;

public class Build {

    public static void main(String[] args) throws IOException {
        boolean showUsage=true;

        if(args.length>1)
        {
            String mode=args[0];
            String restOfArgs[]=Arrays.copyOfRange(args, 1, args.length);

//            if(mode.equals("Compress"))
//            {
//                if(Compress.run(restOfArgs))
//                    showUsage=false;
//            }

            if(mode.equals("Index"))
            {
                if(Index.run(restOfArgs))
                    showUsage=false;
            }

            else if(mode.equals("Feature"))
            {
                if(Feature.run(restOfArgs))
                    showUsage=false;
            }
            else if(mode.equals("Profile"))
            {
                if (Profile.run(restOfArgs))
                    showUsage = false;
            }

        }

        if(showUsage)
        {
            System.err.println("KPP: constructing phylogenetic profile and extracting feature for the prediction of PHI");
            System.err.println("Usage: ");
//            System.err.println("       Compress [-i <Species path>] [-k <Integer>] [-m <Max iteration>]...");
            System.err.println("       Index [-i <Species path>] [-k <Integer>] [-m <Max species>] [-s <The Number of Split file>] [-o <Output file Name>]...");
            System.err.println("   or: ");
            System.err.println("       Profile [-i <Species path>] [-k <Integer>] [-c <Contig index path>] [-o <Output profile path>] [-f <Fuzzy match>]...");
            System.err.println("   or: ");
            System.err.println("       Feature [-i <Species path>] [-k <Integer>] [-p <profile file>] [-o <Output feature file>]...");
            System.exit(1);
        }
    }
    }

