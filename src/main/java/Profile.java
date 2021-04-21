import kmer_contig.CreateSpeciesTable;

import java.io.IOException;

import static utils.checkPath.*;

public class Profile {
    public static boolean run(String[] args) throws IOException {
        int argIdx = 0;
        String spePath = null;
        String contigPath = null;
        String outProfile = null;
        String speNamePath = null;
        int speNum = 1;
        int k = 1;
        String model ="AA";

        while (argIdx < args.length && args[argIdx].startsWith("-")) {
            String arg = args[argIdx++];
            if (arg.equals("-i"))
                spePath = args[argIdx++];
            else if (arg.equals("-c"))
                contigPath = args[argIdx++];
            else if (arg.equals("-o"))
                outProfile = args[argIdx++];
            else if (arg.equals("-d"))
                model = args[argIdx++];
            else if (arg.equals("-a"))
                speNamePath = args[argIdx++];
            else if (arg.equals("-k"))
                k = Integer.parseInt(args[argIdx++]);
            else if (arg.equals("-n"))
                speNum = Integer.parseInt(args[argIdx++]);
            else {
                System.err.println("Unknown option: " + arg);
            }
        }
        spePath = checkInputFile(spePath);
        outProfile = checkOutputFile(outProfile);
        CreateSpeciesTable foo = new CreateSpeciesTable(contigPath,
                spePath,
                outProfile,
                model,
                speNum);
        foo.createSpeEach(k);
        if (null != speNamePath)
            foo.writeSpeName(speNamePath);
        return true;
    }
}
