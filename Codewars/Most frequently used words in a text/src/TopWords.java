    import java.util.Comparator;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Locale;
    import java.util.Map;
    import java.util.stream.Collectors;

    public class TopWords {

        public static List<String> top3(String s) {

            System.out.println(s);
            if (s.isEmpty()) {
                return null;
            }

            Map<String, Integer> wordCounter = new HashMap<>();

            int i = 0;
            for (String word :
                    s.split("[\\s,./;?_:\\-!]")) {
                if (!word.strip().isEmpty()) {
                    if(word.matches("'?[a-zA-Z][[a-zA-Z]']*(?:-=[a-zA-Z]+)*'?")){
                        if(word.equalsIgnoreCase("rwiqpgp"))
                        System.out.println(i++ + " - " + word);
                        if (wordCounter.containsKey(word)) {
                            Integer value = wordCounter.get(word);
                            wordCounter.put(word, value + 1);
                        } else {
                            wordCounter.put(word, 1);
                        }
                    }
                }
            }

            System.out.println(wordCounter);

            return wordCounter.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(Map.Entry::getKey)
                    .map(String::toLowerCase)
                    .limit(3)
                    .collect(Collectors.toList());
        }

        public static void main(String[] args) {
            System.out.println(TopWords.top3("NCzlixM GlL!AXcUtB WKl RWIqPgP kkNLliP GlL pLUKnWkTfA YTwoTGHI uBEsyQyEF kkNLliP_kkNLliP oartwTnpD';NCzlixM oartwTnpD' uBEsyQyEF pLUKnWkTfA?sCr'ABLgo sCr'ABLgo sCr'ABLgo AXcUtB AXcUtB Muncuf KekTsNsOc EzUkjHnsF sCr'ABLgo GOUhoFeFgF.mjYCaaThI_uBEsyQyEF sCr'ABLgo_uBEsyQyEF RWIqPgP NCzlixM ARhhB TmtnaPABQ uBEsyQyEF,oartwTnpD'/uBEsyQyEF uBEsyQyEF AXcUtB GlL?pLUKnWkTfA.EzUkjHnsF;uBEsyQyEF sCr'ABLgo,uBEsyQyEF:PznrSQERo pLUKnWkTfA pLUKnWkTfA;AXcUtB/NCzlixM_oartwTnpD' Muncuf pLUKnWkTfA/sCr'ABLgo AXcUtB?uBEsyQyEF RWIqPgP-GlL TmtnaPABQ KekTsNsOc oartwTnpD' KekTsNsOc?Muncuf pLUKnWkTfA RWIqPgP uBEsyQyEF mjYCaaThI KekTsNsOc-ARhhB KekTsNsOc kkNLliP-uBEsyQyEF!mjYCaaThI_KekTsNsOc uBEsyQyEF.mjYCaaThI.TmtnaPABQ:oartwTnpD' uBEsyQyEF PznrSQERo,TmtnaPABQ;RWIqPgP;sCr'ABLgo,Muncuf mjYCaaThI/WKl GOUhoFeFgF-KekTsNsOc AXcUtB;RWIqPgP!KekTsNsOc-uBEsyQyEF pLUKnWkTfA?GOUhoFeFgF;NCzlixM!AXcUtB uBEsyQyEF/GOUhoFeFgF-TmtnaPABQ ARhhB;mjYCaaThI:RWIqPgP TmtnaPABQ TmtnaPABQ/Muncuf?Muncuf-AXcUtB/uBEsyQyEF kkNLliP,KekTsNsOc TmtnaPABQ ARhhB!KekTsNsOc?AXcUtB;WKl RWIqPgP_oartwTnpD' KekTsNsOc;kkNLliP GOUhoFeFgF/sCr'ABLgo?KekTsNsOc WKl?uBEsyQyEF NCzlixM_WKl uBEsyQyEF-KekTsNsOc!NCzlixM?mjYCaaThI/uBEsyQyEF!TmtnaPABQ AXcUtB/uBEsyQyEF GOUhoFeFgF?WKl WKl,oartwTnpD' WKl uBEsyQyEF sCr'ABLgo pLUKnWkTfA WKl ARhhB/ARhhB_mjYCaaThI sCr'ABLgo_GlL!RWIqPgP NCzlixM;RWIqPgP RWIqPgP?uBEsyQyEF EzUkjHnsF oartwTnpD'/Muncuf TmtnaPABQ ARhhB,GOUhoFeFgF;kkNLliP RWIqPgP ARhhB/sCr'ABLgo PznrSQERo;RWIqPgP AXcUtB TmtnaPABQ.uBEsyQyEF KekTsNsOc?GlL!sCr'ABLgo!YTwoTGHI KekTsNsOc;sCr'ABLgo:pLUKnWkTfA AXcUtB.RWIqPgP;uBEsyQyEF.mjYCaaThI RWIqPgP mjYCaaThI ARhhB ARhhB pLUKnWkTfA.pLUKnWkTfA pLUKnWkTfA EzUkjHnsF:RWIqPgP RWIqPgP ARhhB KekTsNsOc_NCzlixM GOUhoFeFgF pLUKnWkTfA_pLUKnWkTfA TmtnaPABQ:sCr'ABLgo pLUKnWkTfA/KekTsNsOc sCr'ABLgo!Muncuf sCr'ABLgo;KekTsNsOc GOUhoFeFgF RWIqPgP GOUhoFeFgF KekTsNsOc WKl_TmtnaPABQ,pLUKnWkTfA PznrSQERo!kkNLliP pLUKnWkTfA!TmtnaPABQ uBEsyQyEF AXcUtB_mjYCaaThI;mjYCaaThI!pLUKnWkTfA PznrSQERo/KekTsNsOc;pLUKnWkTfA/pLUKnWkTfA pLUKnWkTfA,ARhhB;WKl!EzUkjHnsF!sCr'ABLgo NCzlixM pLUKnWkTfA sCr'ABLgo!RWIqPgP.pLUKnWkTfA sCr'ABLgo!WKl?uBEsyQyEF_sCr'ABLgo RWIqPgP AXcUtB!KekTsNsOc TmtnaPABQ mjYCaaThI!oartwTnpD'/WKl ARhhB!sCr'ABLgo mjYCaaThI KekTsNsOc?GOUhoFeFgF.YTwoTGHI WKl,AXcUtB!mjYCaaThI mjYCaaThI NCzlixM KekTsNsOc;mjYCaaThI.pLUKnWkTfA TmtnaPABQ AXcUtB!TmtnaPABQ_GOUhoFeFgF RWIqPgP mjYCaaThI KekTsNsOc!WKl WKl kkNLliP:WKl pLUKnWkTfA GlL?PznrSQERo.sCr'ABLgo?AXcUtB sCr'ABLgo GlL RWIqPgP oartwTnpD'?RWIqPgP:WKl/mjYCaaThI/RWIqPgP;KekTsNsOc GlL WKl/GOUhoFeFgF-TmtnaPABQ pLUKnWkTfA NCzlixM AXcUtB,KekTsNsOc-NCzlixM uBEsyQyEF EzUkjHnsF;GOUhoFeFgF?GOUhoFeFgF-uBEsyQyEF_TmtnaPABQ?GOUhoFeFgF-NCzlixM/YTwoTGHI mjYCaaThI/sCr'ABLgo-NCzlixM sCr'ABLgo;RWIqPgP_pLUKnWkTfA,Muncuf KekTsNsOc oartwTnpD';WKl-sCr'ABLgo Muncuf PznrSQERo uBEsyQyEF kkNLliP!KekTsNsOc mjYCaaThI-mjYCaaThI GOUhoFeFgF,KekTsNsOc?GOUhoFeFgF_YTwoTGHI EzUkjHnsF kkNLliP RWIqPgP/TmtnaPABQ GlL NCzlixM Muncuf RWIqPgP?RWIqPgP?ARhhB oartwTnpD'"));
        }
    }