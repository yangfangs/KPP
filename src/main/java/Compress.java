import kmer_contig.contig;

public class Compress {


    public static boolean run(String[] args) {
        int argIdx = 0;
        String spePath = null;
        int k = 1;
        int maxInter=1;
        String model="AA";
        while (argIdx < args.length && args[argIdx].startsWith("-")) {
            String arg = args[argIdx++];
            if (arg.equals("-i"))
                spePath = args[argIdx++];
            else if (arg.equals("-k"))
                k = Integer.parseInt(args[argIdx++]);
            else if (arg.equals("-m"))
                maxInter = Integer.parseInt(args[argIdx++]);
            else if (arg.equals("-t"))
                model = args[argIdx++];
            else {
                System.err.println("Unknown option: " + arg);
            }
        }

        contig foo =  new contig(k,spePath,maxInter,model);
        foo.printCompress();

        return true;
    }
}