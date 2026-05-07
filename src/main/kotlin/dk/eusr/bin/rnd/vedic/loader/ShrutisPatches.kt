/*
 * Copyright (c) 2026 Michael E Shorter
 * SPDX-License-Identifier: MIT
 */

package dk.eusr.bin.rnd.vedic.loader

/**
 * Manual corrections for known formatting errors in the ITX source files.
 *
 * The source HTML from SanskritDocuments.org occasionally contains lines that deviate
 * from the expected verse-numbering format -- trailing `<BR>` tags inside the verse
 * marker, swapped varga/line fields (especially in Rigveda Mandala 8), encoding
 * artefacts, or embedded reference legends.
 *
 * Each entry maps the raw line (exactly as it appears in the source) to the corrected
 * form. [ShrutisLoad.load] applies these patches before attempting regex parsing.
 * New patch candidates are generated automatically into `output/vedas-patches.txt`
 * by [Utils.writePatches] for manual review.
 */
object ShrutisPatches {
    val patches = mapOf(
        "RV_04.001.11.b apād aśīrṣā guhamāno antāyoyuvāno vṛṣabhasya nīḷe<BR>" to
            "RV_04.001.11.b apād aśīrṣā guhamāno antāyoyuvāno vṛṣabhasya nīḷe",
        "RV_04.013.05.b kayā yāti svadhayā ko dadarśa diva skambhaḥ samṛtaḥ pāti nākam <BR>" to
            "RV_04.013.05.b kayā yāti svadhayā ko dadarśa diva skambhaḥ samṛtaḥ pāti nākam",

        "RV_08.068.{01}1 . ā tvā rathaṃ yathotaye sumnāya vartayāmasi | <BR>" to
            "RV_08.068.1{01}  ā tvā rathaṃ yathotaye sumnāya vartayāmasi",
        "RV_08.068.{01}2 . tuvikūrmim ṛtīṣaham indra śaviṣṭha satpate || <BR>" to
            "RV_08.068.2{01}  tuvikūrmim ṛtīṣaham indra śaviṣṭha satpate",
        "RV_08.068.{02}1 . tuviśuṣma tuvikrato śacīvo viśvayā mate | <BR>" to
            "RV_08.068.1{02}  tuviśuṣma tuvikrato śacīvo viśvayā mate",
        "RV_08.068.{02}2 . ā paprātha mahitvanā || <BR>" to
            "RV_08.068.2{02}  ā paprātha mahitvanā",
        "RV_08.068.{03}1 . yasya te mahinā mahaḥ pari jmāyantam īyatuḥ | <BR>" to
            "RV_08.068.1{03}  yasya te mahinā mahaḥ pari jmāyantam īyatuḥ",
        "RV_08.068.{03}2 . hastā vajraṃ hiraṇyayam || <BR>" to
            "RV_08.068.{03}2 . hastā vajraṃ hiraṇyayam",
        "RV_08.068.{04}1 . viśvānarasya vas patim anānatasya śavasaḥ | <BR>" to
            "RV_08.068.{04}1 . viśvānarasya vas patim anānatasya śavasaḥ",
        "RV_08.068.{04}2 . evaiś ca carṣaṇīnām ūtī huve rathānām || <BR>" to
            "RV_08.068.{04}2 . evaiś ca carṣaṇīnām ūtī huve rathānām",
        "RV_08.068.{05}1 . abhiṣṭaye sadāvṛdhaṃ svarmīḷheṣu yaṃ naraḥ | <BR>" to
            "RV_08.068.{05}1 . abhiṣṭaye sadāvṛdhaṃ svarmīḷheṣu yaṃ naraḥ",
        "RV_08.068.{05}2 . nānā havanta ūtaye || <BR>" to
            "RV_08.068.{05}2 . nānā havanta ūtaye",
        "RV_08.068.{06}1 . paromātram ṛcīṣamam indram ugraṃ surādhasam | <BR>" to
            "RV_08.068.{06}1 . paromātram ṛcīṣamam indram ugraṃ surādhasam",
        "RV_08.068.{06}2 . īśānaṃ cid vasūnām || <BR>" to
            "RV_08.068.{06}2 . īśānaṃ cid vasūnām",
        "RV_08.068.{07}1 . taṃ-tam id rādhase maha indraṃ codāmi pītaye | <BR>" to
            "RV_08.068.{07}1 . taṃ-tam id rādhase maha indraṃ codāmi pītaye",
        "RV_08.068.{07}2 . yaḥ pūrvyām anuṣṭutim īśe kṛṣṭīnāṃ nṛtuḥ || <BR>" to
            "RV_08.068.{07}2 . yaḥ pūrvyām anuṣṭutim īśe kṛṣṭīnāṃ nṛtuḥ",
        "RV_08.068.{08}1 . na yasya te śavasāna sakhyam ānaṃśa martyaḥ | <BR>" to
            "RV_08.068.{08}1 . na yasya te śavasāna sakhyam ānaṃśa martyaḥ",
        "RV_08.068.{08}2 . nakiḥ śavāṃsi te naśat || <BR>" to
            "RV_08.068.{08}2 . nakiḥ śavāṃsi te naśat",
        "RV_08.068.{09}1 . tvotāsas tvā yujāpsu sūrye mahad dhanam | <BR>" to
            "RV_08.068.{09}1 . tvotāsas tvā yujāpsu sūrye mahad dhanam",
        "RV_08.068.{09}2 . jayema pṛtsu vajrivaḥ || <BR>" to
            "RV_08.068.{09}2 . jayema pṛtsu vajrivaḥ",
        "RV_08.068.{10}1 . taṃ tvā yajñebhir īmahe taṃ gīrbhir girvaṇastama | <BR>" to
            "RV_08.068.{10}1 . taṃ tvā yajñebhir īmahe taṃ gīrbhir girvaṇastama",
        "RV_08.068.{10}2 . indra yathā cid āvitha vājeṣu purumāyyam || <BR>" to
            "RV_08.068.{10}2 . indra yathā cid āvitha vājeṣu purumāyyam",
        "RV_08.068.{11}1 . yasya te svādu sakhyaṃ svādvī praṇītir adrivaḥ | <BR>" to
            "RV_08.068.{11}1 . yasya te svādu sakhyaṃ svādvī praṇītir adrivaḥ",
        "RV_08.068.{11}2 . yajño vitantasāyyaḥ || <BR>" to
            "RV_08.068.{11}2 . yajño vitantasāyyaḥ",
        "RV_08.068.{12}1 . uru ṇas tanve tana uru kṣayāya nas kṛdhi | <BR>" to
            "RV_08.068.{12}1 . uru ṇas tanve tana uru kṣayāya nas kṛdhi",
        "RV_08.068.{12}2 . uru ṇo yandhi jīvase || <BR>" to
            "RV_08.068.{12}2 . uru ṇo yandhi jīvase",
        "RV_08.068.{13}1 . uruṃ nṛbhya uruṃ gava uruṃ rathāya panthām | <BR>" to
            "RV_08.068.{13}1 . uruṃ nṛbhya uruṃ gava uruṃ rathāya panthām",
        "RV_08.068.{13}2 . devavītim manāmahe || <BR>" to
            "RV_08.068.{13}2 . devavītim manāmahe",
        "RV_08.068.{14}1 . upa mā ṣaḍ dvā-dvā naraḥ somasya harṣyā | <BR>" to
            "RV_08.068.{14}1 . upa mā ṣaḍ dvā-dvā naraḥ somasya harṣyā",
        "RV_08.068.{14}2 . tiṣṭhanti svādurātayaḥ || <BR>" to
            "RV_08.068.{14}2 . tiṣṭhanti svādurātayaḥ",
        "RV_08.068.{15}1 . ṛjrāv indrota ā dade harī ṛkṣasya sūnavi | <BR>" to
            "RV_08.068.{15}1 . ṛjrāv indrota ā dade harī ṛkṣasya sūnavi",
        "RV_08.068.{15}2 . āśvamedhasya rohitā || <BR>" to
            "RV_08.068.{15}2 . āśvamedhasya rohitā",
        "RV_08.068.{16}1 . surathāṃ ātithigve svabhīśūṃr ārkṣe | <BR>" to
            "RV_08.068.{16}1 . surathāṃ ātithigve svabhīśūṃr ārkṣe",
        "RV_08.068.{16}2 . āśvamedhe supeśasaḥ || <BR>" to
            "RV_08.068.{16}2 . āśvamedhe supeśasaḥ",
        "RV_08.068.{17}1 . ṣaḷ aśvāṃ ātithigva indrote vadhūmataḥ | <BR>" to
            "RV_08.068.{17}1 . ṣaḷ aśvāṃ ātithigva indrote vadhūmataḥ",
        "RV_08.068.{17}2 . sacā pūtakratau sanam || <BR>" to
            "RV_08.068.{17}2 . sacā pūtakratau sanam",
        "RV_08.068.{18}1 . aiṣu cetad vṛṣaṇvaty antar ṛjreṣv aruṣī | <BR>" to
            "RV_08.068.{18}1 . aiṣu cetad vṛṣaṇvaty antar ṛjreṣv aruṣī",
        "RV_08.068.{18}2 . svabhīśuḥ kaśāvatī || <BR>" to
            "RV_08.068.{18}2 . svabhīśuḥ kaśāvatī",
        "RV_08.068.{19}1 . na yuṣme vājabandhavo ninitsuś cana martyaḥ | <BR>" to
            "RV_08.068.{19}1 . na yuṣme vājabandhavo ninitsuś cana martyaḥ",
        "RV_08.068.{19}2 . avadyam adhi dīdharat || <BR>" to
            "RV_08.068.{19}2 . avadyam adhi dīdharat",
        "RV_08.069.{01}1 . pra-pra vas triṣṭubham iṣam mandadvīrāyendave | <BR>" to
            "RV_08.069.{01}1 . pra-pra vas triṣṭubham iṣam mandadvīrāyendave",
        "RV_08.069.{01}2 . dhiyā vo medhasātaye puraṃdhyā vivāsati || <BR>" to
            "RV_08.069.{01}2 . dhiyā vo medhasātaye puraṃdhyā vivāsati",
        "RV_08.069.{02}1 . nadaṃ va odatīnāṃ nadaṃ yoyuvatīnām | <BR>" to
            "RV_08.069.{02}1 . nadaṃ va odatīnāṃ nadaṃ yoyuvatīnām",
        "RV_08.069.{02}2 . patiṃ vo aghnyānāṃ dhenūnām iṣudhyasi || <BR>" to
            "RV_08.069.{02}2 . patiṃ vo aghnyānāṃ dhenūnām iṣudhyasi",
        "RV_08.069.{03}1 . tā asya sūdadohasaḥ somaṃ śrīṇanti pṛśnayaḥ | <BR>" to
            "RV_08.069.{03}1 . tā asya sūdadohasaḥ somaṃ śrīṇanti pṛśnayaḥ",
        "RV_08.069.{03}2 . janman devānāṃ viśas triṣv ā rocane divaḥ || <BR>" to
            "RV_08.069.{03}2 . janman devānāṃ viśas triṣv ā rocane divaḥ",
        "RV_08.069.{04}1 . abhi pra gopatiṃ girendram arca yathā vide | <BR>" to
            "RV_08.069.{04}1 . abhi pra gopatiṃ girendram arca yathā vide",
        "RV_08.069.{04}2 . sūnuṃ satyasya satpatim || <BR>" to
            "RV_08.069.{04}2 . sūnuṃ satyasya satpatim",
        "RV_08.069.{05}1 . ā harayaḥ sasṛjrire 'ruṣīr adhi barhiṣi | <BR>" to
            "RV_08.069.{05}1 . ā harayaḥ sasṛjrire 'ruṣīr adhi barhiṣi",
        "RV_08.069.{05}2 . yatrābhi saṃnavāmahe || <BR>" to
            "RV_08.069.{05}2 . yatrābhi saṃnavāmahe",
        "RV_08.069.{06}1 . indrāya gāva āśiraṃ duduhre vajriṇe madhu | <BR>" to
            "RV_08.069.{06}1 . indrāya gāva āśiraṃ duduhre vajriṇe madhu",
        "RV_08.069.{06}2 . yat sīm upahvare vidat || <BR>" to
            "RV_08.069.{06}2 . yat sīm upahvare vidat",
        "RV_08.069.{07}1 . ud yad bradhnasya viṣṭapaṃ gṛham indraś ca ganvahi | <BR>" to
            "RV_08.069.{07}1 . ud yad bradhnasya viṣṭapaṃ gṛham indraś ca ganvahi",
        "RV_08.069.{07}2 . madhvaḥ pītvā sacevahi triḥ sapta sakhyuḥ pade || <BR>" to
            "RV_08.069.{07}2 . madhvaḥ pītvā sacevahi triḥ sapta sakhyuḥ pade",
        "RV_08.069.{08}1 . arcata prārcata priyamedhāso arcata | <BR>" to
            "RV_08.069.{08}1 . arcata prārcata priyamedhāso arcata",
        "RV_08.069.{08}2 . arcantu putrakā uta puraṃ na dhṛṣṇv arcata || <BR>" to
            "RV_08.069.{08}2 . arcantu putrakā uta puraṃ na dhṛṣṇv arcata",
        "RV_08.069.{09}1 . ava svarāti gargaro godhā pari saniṣvaṇat | <BR>" to
            "RV_08.069.{09}1 . ava svarāti gargaro godhā pari saniṣvaṇat",
        "RV_08.069.{09}2 . piOgā pari caniṣkadad indrāya brahmodyatam || <BR>" to
            "RV_08.069.{09}2 . piOgā pari caniṣkadad indrāya brahmodyatam",
        "RV_08.069.{10}1 . ā yat patanty enyaḥ sudughā anapasphuraḥ | <BR>" to
            "RV_08.069.{10}1 . ā yat patanty enyaḥ sudughā anapasphuraḥ",
        "RV_08.069.{10}2 . apasphuraṃ gṛbhāyata somam indrāya pātave || <BR>" to
            "RV_08.069.{10}2 . apasphuraṃ gṛbhāyata somam indrāya pātave",
        "RV_08.069.{11}1 . apād indro apād agnir viśve devā amatsata | <BR>" to
            "RV_08.069.{11}1 . apād indro apād agnir viśve devā amatsata",
        "RV_08.069.{11}2 . varuṇa id iha kṣayat tam āpo abhy anūṣata vatsaṃ saṃśiśvarīr iva || <BR>" to
            "RV_08.069.{11}2 . varuṇa id iha kṣayat tam āpo abhy anūṣata vatsaṃ saṃśiśvarīr iva",
        "RV_08.069.{12}1 . sudevo asi varuṇa yasya te sapta sindhavaḥ | <BR>" to
            "RV_08.069.{12}1 . sudevo asi varuṇa yasya te sapta sindhavaḥ",
        "RV_08.069.{12}2 . anukṣaranti kākudaṃ sūrmyaṃ suṣirām iva || <BR>" to
            "RV_08.069.{12}2 . anukṣaranti kākudaṃ sūrmyaṃ suṣirām iva",
        "RV_08.069.{13}1 . yo vyatīṃr aphāṇayat suyuktāṃ upa dāśuṣe | <BR>" to
            "RV_08.069.{13}1 . yo vyatīṃr aphāṇayat suyuktāṃ upa dāśuṣe",
        "RV_08.069.{13}2 . takvo netā tad id vapur upamā yo amucyata || <BR>" to
            "RV_08.069.{13}2 . takvo netā tad id vapur upamā yo amucyata",
        "RV_08.069.{14}1 . atīd u śakra ohata indro viśvā ati dviṣaḥ | <BR>" to
            "RV_08.069.{14}1 . atīd u śakra ohata indro viśvā ati dviṣaḥ",
        "RV_08.069.{14}2 . bhinat kanīna odanam pacyamānam paro girā || <BR>" to
            "RV_08.069.{14}2 . bhinat kanīna odanam pacyamānam paro girā",
        "RV_08.069.{15}1 . arbhako na kumārako 'dhi tiṣṭhan navaṃ ratham | <BR>" to
            "RV_08.069.{15}1 . arbhako na kumārako 'dhi tiṣṭhan navaṃ ratham",
        "RV_08.069.{15}2 . sa pakṣan mahiṣam mṛgam pitre mātre vibhukratum || <BR>" to
            "RV_08.069.{15}2 . sa pakṣan mahiṣam mṛgam pitre mātre vibhukratum",
        "RV_08.069.{16}1 . ā tū suśipra dampate rathaṃ tiṣṭhā hiraṇyayam | <BR>" to
            "RV_08.069.{16}1 . ā tū suśipra dampate rathaṃ tiṣṭhā hiraṇyayam",
        "RV_08.069.{16}2 . adha dyukṣaṃ sacevahi sahasrapādam aruṣaṃ svastigām anehasam || <BR>" to
            "RV_08.069.{16}2 . adha dyukṣaṃ sacevahi sahasrapādam aruṣaṃ svastigām anehasam",
        "RV_08.069.{17}1 . taṃ ghem itthā namasvina upa svarājam āsate | <BR>" to
            "RV_08.069.{17}1 . taṃ ghem itthā namasvina upa svarājam āsate",
        "RV_08.069.{17}2 . arthaṃ cid asya sudhitaṃ yad etava āvartayanti dāvane || <BR>" to
            "RV_08.069.{17}2 . arthaṃ cid asya sudhitaṃ yad etava āvartayanti dāvane",
        "RV_08.069.{18}1 . anu pratnasyaukasaḥ priyamedhāsa eṣām | <BR>" to
            "RV_08.069.{18}1 . anu pratnasyaukasaḥ priyamedhāsa eṣām",
        "RV_08.069.{18}2 . pūrvām anu prayatiṃ vṛktabarhiṣo hitaprayasa āśata || <BR>" to
            "RV_08.069.{18}2 . pūrvām anu prayatiṃ vṛktabarhiṣo hitaprayasa āśata",
        "RV_08.070.{01}1 . yo rājā carṣaṇīnāṃ yātā rathebhir adhriguḥ | <BR>" to
            "RV_08.070.{01}1 . yo rājā carṣaṇīnāṃ yātā rathebhir adhriguḥ",
        "RV_08.070.{01}2 . viśvāsāṃ tarutā pṛtanānāṃ jyeṣṭho yo vṛtrahā gṛṇe || <BR>" to
            "RV_08.070.{01}2 . viśvāsāṃ tarutā pṛtanānāṃ jyeṣṭho yo vṛtrahā gṛṇe",
        "RV_08.070.{02}1 . indraṃ taṃ śumbha puruhanmann avase yasya dvitā vidhartari | <BR>" to
            "RV_08.070.{02}1 . indraṃ taṃ śumbha puruhanmann avase yasya dvitā vidhartari",
        "RV_08.070.{02}2 . hastāya vajraḥ prati dhāyi darśato maho dive na sūryaḥ || <BR>" to
            "RV_08.070.{02}2 . hastāya vajraḥ prati dhāyi darśato maho dive na sūryaḥ",
        "RV_08.070.{03}1 . nakiṣ ṭaṃ karmaṇā naśad yaś cakāra sadāvṛdham | <BR>" to
            "RV_08.070.{03}1 . nakiṣ ṭaṃ karmaṇā naśad yaś cakāra sadāvṛdham",
        "RV_08.070.{03}2 . indraṃ na yajñair viśvagūrtam ṛbhvasam adhṛṣṭaṃ dhṛṣṇvïjasam || <BR>" to
            "RV_08.070.{03}2 . indraṃ na yajñair viśvagūrtam ṛbhvasam adhṛṣṭaṃ dhṛṣṇvïjasam",
        "RV_08.070.{04}1 . aṣāḷham ugram pṛtanāsu sāsahiṃ yasmin mahīr urujrayaḥ | <BR>" to
            "RV_08.070.{04}1 . aṣāḷham ugram pṛtanāsu sāsahiṃ yasmin mahīr urujrayaḥ",
        "RV_08.070.{04}2 . saṃ dhenavo jāyamāne anonavur dyāvaḥ kṣāmo anonavuḥ || <BR>" to
            "RV_08.070.{04}2 . saṃ dhenavo jāyamāne anonavur dyāvaḥ kṣāmo anonavuḥ",
        "RV_08.070.{05}1 . yad dyāva indra te śataṃ śatam bhūmīr uta syuḥ | <BR>" to
            "RV_08.070.{05}1 . yad dyāva indra te śataṃ śatam bhūmīr uta syuḥ",
        "RV_08.070.{05}2 . na tvā vajrin sahasraṃ sūryā anu na jātam aṣṭa rodasī || <BR>" to
            "RV_08.070.{05}2 . na tvā vajrin sahasraṃ sūryā anu na jātam aṣṭa rodasī",
        "RV_08.070.{06}1 . ā paprātha mahinā vṛṣṇyā vṛṣan viśvā śaviṣṭha śavasā | <BR>" to
            "RV_08.070.{06}1 . ā paprātha mahinā vṛṣṇyā vṛṣan viśvā śaviṣṭha śavasā",
        "RV_08.070.{06}2 . asmāṃ ava maghavan gomati vraje vajriñ citrābhir ūtibhiḥ || <BR>" to
            "RV_08.070.{06}2 . asmāṃ ava maghavan gomati vraje vajriñ citrābhir ūtibhiḥ",
        "RV_08.070.{07}1 . na sīm adeva āpad iṣaṃ dīrghāyo martyaḥ | <BR>" to
            "RV_08.070.{07}1 . na sīm adeva āpad iṣaṃ dīrghāyo martyaḥ",
        "RV_08.070.{07}2 . etagvā cid ya etaśā yuyojate harī indro yuyojate || <BR>" to
            "RV_08.070.{07}2 . etagvā cid ya etaśā yuyojate harī indro yuyojate",
        "RV_08.070.{08}1 . taṃ vo maho mahāyyam indraṃ dānāya sakṣaṇim | <BR>" to
            "RV_08.070.{08}1 . taṃ vo maho mahāyyam indraṃ dānāya sakṣaṇim",
        "RV_08.070.{08}2 . yo gādheṣu ya āraṇeṣu havyo vājeṣv asti havyaḥ || <BR>" to
            "RV_08.070.{08}2 . yo gādheṣu ya āraṇeṣu havyo vājeṣv asti havyaḥ",
        "RV_08.070.{09}1 . ud ū ṣu ṇo vaso mahe mṛśasva śūra rādhase | <BR>" to
            "RV_08.070.{09}1 . ud ū ṣu ṇo vaso mahe mṛśasva śūra rādhase",
        "RV_08.070.{09}2 . ud ū ṣu mahyai maghavan maghattaya ud indra śravase mahe || <BR>" to
            "RV_08.070.{09}2 . ud ū ṣu mahyai maghavan maghattaya ud indra śravase mahe",
        "RV_08.070.{10}1 . tvaṃ na indra ṛtayus tvānido ni tṛmpasi | <BR>" to
            "RV_08.070.{10}1 . tvaṃ na indra ṛtayus tvānido ni tṛmpasi",
        "RV_08.070.{10}2 . madhye vasiṣva tuvinṛmṇorvor ni dāsaṃ śiśnatho hathaiḥ || <BR>" to
            "RV_08.070.{10}2 . madhye vasiṣva tuvinṛmṇorvor ni dāsaṃ śiśnatho hathaiḥ",
        "RV_08.070.{11}1 . anyavratam amānuṣam ayajvānam adevayum | <BR>" to
            "RV_08.070.{11}1 . anyavratam amānuṣam ayajvānam adevayum",
        "RV_08.070.{11}2 . ava svaḥ sakhā dudhuvīta parvataḥ sughnāya dasyum parvataḥ || <BR>" to
            "RV_08.070.{11}2 . ava svaḥ sakhā dudhuvīta parvataḥ sughnāya dasyum parvataḥ",
        "RV_08.070.{12}1 . tvaṃ na indrāsāṃ haste śaviṣṭha dāvane | <BR>" to
            "RV_08.070.{12}1 . tvaṃ na indrāsāṃ haste śaviṣṭha dāvane",
        "RV_08.070.{12}2 . dhānānāṃ na saṃ gṛbhāyāsmayur dviḥ saṃ gṛbhāyāsmayuḥ || <BR>" to
            "RV_08.070.{12}2 . dhānānāṃ na saṃ gṛbhāyāsmayur dviḥ saṃ gṛbhāyāsmayuḥ",
        "RV_08.070.{13}1 . sakhāyaḥ kratum ichata kathā rādhāma śarasya | <BR>" to
            "RV_08.070.{13}1 . sakhāyaḥ kratum ichata kathā rādhāma śarasya",
        "RV_08.070.{13}2 . upastutim bhojaḥ sūrir yo ahrayaḥ || <BR>" to
            "RV_08.070.{13}2 . upastutim bhojaḥ sūrir yo ahrayaḥ",
        "RV_08.070.{14}1 . bhūribhiḥ samaha ṛṣibhir barhiṣmadbhi staviṣyase | <BR>" to
            "RV_08.070.{14}1 . bhūribhiḥ samaha ṛṣibhir barhiṣmadbhi staviṣyase",
        "RV_08.070.{14}2 . yad ittham ekam-ekam ic chara vatsān parādadaḥ || <BR>" to
            "RV_08.070.{14}2 . yad ittham ekam-ekam ic chara vatsān parādadaḥ",
        "RV_08.070.{15}1 . karṇagṛhyā maghavā śauradevyo vatsaṃ nas tribhya ānayat | <BR>" to
            "RV_08.070.{15}1 . karṇagṛhyā maghavā śauradevyo vatsaṃ nas tribhya ānayat",
        "RV_08.070.{15}2 . ajāṃ sūrir na dhātave || <BR>" to
            "RV_08.070.{15}2 . ajāṃ sūrir na dhātave",
        "RV_08.071.{01}1 . tvaṃ no agne mahobhiḥ pāhi viśvasyā arāteḥ | <BR>" to
            "RV_08.071.{01}1 . tvaṃ no agne mahobhiḥ pāhi viśvasyā arāteḥ",
        "RV_08.071.{01}2 . uta dviṣo martyasya || <BR>" to
            "RV_08.071.{01}2 . uta dviṣo martyasya",
        "RV_08.071.{02}1 . nahi manyuḥ pauruṣeya īśe hi vaḥ priyajāta | <BR>" to
            "RV_08.071.{02}1 . nahi manyuḥ pauruṣeya īśe hi vaḥ priyajāta",
        "RV_08.071.{02}2 . tvam id asi kṣapāvān || <BR>" to
            "RV_08.071.{02}2 . tvam id asi kṣapāvān",
        "RV_08.071.{03}1 . sa no viśvebhir devebhir ūrjo napād bhadraśoce | <BR>" to
            "RV_08.071.{03}1 . sa no viśvebhir devebhir ūrjo napād bhadraśoce",
        "RV_08.071.{03}2 . rayiṃ dehi viśvavāram || <BR>" to
            "RV_08.071.{03}2 . rayiṃ dehi viśvavāram",
        "RV_08.071.{04}1 . na tam agne arātayo martaṃ yuvanta rāyaḥ | <BR>" to
            "RV_08.071.{04}1 . na tam agne arātayo martaṃ yuvanta rāyaḥ",
        "RV_08.071.{04}2 . yaṃ trāyase dāśvāṃsam || <BR>" to
            "RV_08.071.{04}2 . yaṃ trāyase dāśvāṃsam",
        "RV_08.071.{05}1 . yaṃ tvaṃ vipra medhasātāv agne hinoṣi dhanāya | <BR>" to
            "RV_08.071.{05}1 . yaṃ tvaṃ vipra medhasātāv agne hinoṣi dhanāya",
        "RV_08.071.{05}2 . sa tavotī goṣu gantā || <BR>" to
            "RV_08.071.{05}2 . sa tavotī goṣu gantā",
        "RV_08.071.{06}1 . tvaṃ rayim puruvīram agne dāśuṣe martāya | <BR>" to
            "RV_08.071.{06}1 . tvaṃ rayim puruvīram agne dāśuṣe martāya",
        "RV_08.071.{06}2 . pra ṇo naya vasyo acha || <BR>" to
            "RV_08.071.{06}2 . pra ṇo naya vasyo acha",
        "RV_08.071.{07}1 . uruṣyā ṇo mā parā dā aghāyate jātavedaḥ | <BR>" to
            "RV_08.071.{07}1 . uruṣyā ṇo mā parā dā aghāyate jātavedaḥ",
        "RV_08.071.{07}2 . durādhye martāya || <BR>" to
            "RV_08.071.{07}2 . durādhye martāya",
        "RV_08.071.{08}1 . agne mākiṣ ṭe devasya rātim adevo yuyota | <BR>" to
            "RV_08.071.{08}1 . agne mākiṣ ṭe devasya rātim adevo yuyota",
        "RV_08.071.{08}2 . tvam īśiṣe vasūnām || <BR>" to
            "RV_08.071.{08}2 . tvam īśiṣe vasūnām",
        "RV_08.071.{09}1 . sa no vasva upa māsy ūrjo napān māhinasya | <BR>" to
            "RV_08.071.{09}1 . sa no vasva upa māsy ūrjo napān māhinasya",
        "RV_08.071.{09}2 . sakhe vaso jaritṛbhyaḥ || <BR>" to
            "RV_08.071.{09}2 . sakhe vaso jaritṛbhyaḥ",
        "RV_08.071.{10}1 . achā naḥ śīraśociṣaṃ giro yantu darśatam | <BR>" to
            "RV_08.071.{10}1 . achā naḥ śīraśociṣaṃ giro yantu darśatam",
        "RV_08.071.{10}2 . achā yajñāso namasā purūvasum purupraśastam ūtaye || <BR>" to
            "RV_08.071.{10}2 . achā yajñāso namasā purūvasum purupraśastam ūtaye",
        "RV_08.071.{11}1 . agniṃ sūnuṃ sahaso jātavedasaṃ dānāya vāryāṇām | <BR>" to
            "RV_08.071.{11}1 . agniṃ sūnuṃ sahaso jātavedasaṃ dānāya vāryāṇām",
        "RV_08.071.{11}2 . dvitā yo bhūd amṛto martyeṣv ā hotā mandratamo viśi || <BR>" to
            "RV_08.071.{11}2 . dvitā yo bhūd amṛto martyeṣv ā hotā mandratamo viśi",
        "RV_08.071.{12}1 . agniṃ vo devayajyayāgnim prayaty adhvare | <BR>" to
            "RV_08.071.{12}1 . agniṃ vo devayajyayāgnim prayaty adhvare",
        "RV_08.071.{12}2 . agniṃ dhīṣu prathamam agnim arvaty agniṃ kṣaitrāya sādhase || <BR>" to
            "RV_08.071.{12}2 . agniṃ dhīṣu prathamam agnim arvaty agniṃ kṣaitrāya sādhase",
        "RV_08.071.{13}1 . agnir iṣāṃ sakhye dadātu na īśe yo vāryāṇām | <BR>" to
            "RV_08.071.{13}1 . agnir iṣāṃ sakhye dadātu na īśe yo vāryāṇām",
        "RV_08.071.{13}2 . agniṃ toke tanaye śaśvad īmahe vasuṃ santaṃ tanūpām || <BR>" to
            "RV_08.071.{13}2 . agniṃ toke tanaye śaśvad īmahe vasuṃ santaṃ tanūpām",
        "RV_08.071.{14}1 . agnim īḷiṣvāvase gāthābhiḥ śīraśociṣam | <BR>" to
            "RV_08.071.{14}1 . agnim īḷiṣvāvase gāthābhiḥ śīraśociṣam",
        "RV_08.071.{14}2 . agniṃ rāye purumīḷha śrutaṃ naro 'gniṃ sudītaye chardiḥ || <BR>" to
            "RV_08.071.{14}2 . agniṃ rāye purumīḷha śrutaṃ naro 'gniṃ sudītaye chardiḥ",
        "RV_08.071.{15}1 . agniṃ dveṣo yotavai no gṛṇīmasy agniṃ śaṃ yoś ca dātave | <BR>" to
            "RV_08.071.{15}1 . agniṃ dveṣo yotavai no gṛṇīmasy agniṃ śaṃ yoś ca dātave",
        "RV_08.071.{15}2 . viśvāsu vikṣv aviteva havyo bhuvad vastur ṛṣūṇām ||   <BR>" to
            "RV_08.071.{15}2 . viśvāsu vikṣv aviteva havyo bhuvad vastur ṛṣūṇām",

        "RV_08.102.c\".ê{ā@}i dadhā padaāÅ` <BR>" to
            "RV_08.102.c\".ê{ā@}i dadhā padaāÅ`",

        "MS_n,nn.nn =  Maitrāyaṇī-Saṃhitā_Kāṇḍa,Prapāṭhaka.Anuvāka<br>" to
            "MS_n,nn.nn =  Maitrāyaṇī-Saṃhitā_Kāṇḍa,Prapāṭhaka.Anuvāka<br>",
    )
}
