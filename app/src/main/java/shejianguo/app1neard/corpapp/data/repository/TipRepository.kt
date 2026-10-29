package shejianguo.app1neard.corpapp.data.repository

import shejianguo.app1neard.corpapp.data.model.TipModel
import shejianguo.app1neard.corpapp.data.model.TipText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TipRepository {
    private val tips = listOf(
        TipModel(
            id = 1,
            texts = mapOf(
                "en" to TipText(
                    title = "Stay Hydrated",
                    message = "Drink water before, during, and after training to keep your muscles performing.",
                ),
                "de" to TipText(title = "Genug trinken", message = "Trinke vor, während und nach dem Training Wasser, damit deine Muskeln leistungsfähig bleiben."),
                "es" to TipText(title = "Mantente hidratado", message = "Bebe agua antes, durante y después de entrenar para mantener el rendimiento muscular."),
                "fr" to TipText(title = "Restez hydraté", message = "Buvez avant, pendant et après l’entraînement pour préserver les performances de vos muscles."),
                "hi" to TipText(title = "पानी पीते रहें", message = "मांसपेशियों के बेहतर प्रदर्शन के लिए ट्रेनिंग से पहले, दौरान और बाद में पानी पिएँ।"),
                "id" to TipText(title = "Tetap Terhidrasi", message = "Minum air sebelum, selama, dan sesudah latihan agar otot tetap bekerja optimal."),
                "pt" to TipText(title = "Mantenha-se hidratado", message = "Beba água antes, durante e depois do treino para manter o desempenho dos músculos."),
                "ar" to TipText(title = "حافظ على الترطيب", message = "اشرب الماء قبل التدريب وخلاله وبعده للحفاظ على أداء عضلاتك."),
            ),
        ),
        TipModel(
            id = 2,
            texts = mapOf(
                "en" to TipText(
                    title = "Warm Up First",
                    message = "Five minutes of light movement prepares your joints and lowers injury risk.",
                ),
                "de" to TipText(title = "Erst aufwärmen", message = "Fünf Minuten leichte Bewegung bereiten deine Gelenke vor und senken das Verletzungsrisiko."),
                "es" to TipText(title = "Calienta primero", message = "Cinco minutos de movimiento suave preparan tus articulaciones y reducen el riesgo de lesión."),
                "fr" to TipText(title = "Échauffez-vous d’abord", message = "Cinq minutes de mouvements légers préparent vos articulations et réduisent le risque de blessure."),
                "hi" to TipText(title = "पहले वार्म अप", message = "पाँच मिनट की हल्की गतिविधि जोड़ों को तैयार करती है और चोट का जोखिम घटाती है।"),
                "id" to TipText(title = "Pemanasan Dulu", message = "Lima menit gerakan ringan menyiapkan sendi dan mengurangi risiko cedera."),
                "pt" to TipText(title = "Aqueça primeiro", message = "Cinco minutos de movimento leve preparam as articulações e reduzem o risco de lesão."),
                "ar" to TipText(title = "ابدأ بالإحماء", message = "خمس دقائق من الحركة الخفيفة تهيئ مفاصلك وتقلل خطر الإصابة."),
            ),
        ),
        TipModel(
            id = 3,
            texts = mapOf(
                "en" to TipText(
                    title = "Sleep Well",
                    message = "Seven to nine hours of sleep is when your body actually builds strength.",
                ),
                "de" to TipText(title = "Gut schlafen", message = "In sieben bis neun Stunden Schlaf baut dein Körper tatsächlich Kraft auf."),
                "es" to TipText(title = "Duerme bien", message = "Durante siete a nueve horas de sueño tu cuerpo desarrolla fuerza de verdad."),
                "fr" to TipText(title = "Dormez bien", message = "Sept à neuf heures de sommeil permettent à votre corps de développer sa force."),
                "hi" to TipText(title = "अच्छी नींद लें", message = "सात से नौ घंटे की नींद में आपका शरीर सच में ताकत बनाता है।"),
                "id" to TipText(title = "Tidur Cukup", message = "Tidur tujuh hingga sembilan jam membantu tubuh benar-benar membangun kekuatan."),
                "pt" to TipText(title = "Durma bem", message = "É durante sete a nove horas de sono que seu corpo realmente ganha força."),
                "ar" to TipText(title = "نم جيدًا", message = "خلال سبع إلى تسع ساعات من النوم يبني جسمك قوته فعليًا."),
            ),
        ),
        TipModel(
            id = 4,
            texts = mapOf(
                "en" to TipText(
                    title = "Progress Gradually",
                    message = "Add a little weight or time each week instead of jumping ahead too fast.",
                ),
                "de" to TipText(title = "Langsam steigern", message = "Erhöhe Gewicht oder Zeit jede Woche etwas, statt zu schnell voranzuspringen."),
                "es" to TipText(title = "Progresa poco a poco", message = "Añade un poco de peso o tiempo cada semana en lugar de avanzar demasiado rápido."),
                "fr" to TipText(title = "Progressez doucement", message = "Ajoutez un peu de poids ou de temps chaque semaine sans aller trop vite."),
                "hi" to TipText(title = "धीरे-धीरे बढ़ें", message = "बहुत तेज़ बढ़ने के बजाय हर हफ़्ते थोड़ा वज़न या समय जोड़ें।"),
                "id" to TipText(title = "Tingkatkan Bertahap", message = "Tambah sedikit beban atau waktu tiap minggu, jangan melaju terlalu cepat."),
                "pt" to TipText(title = "Progrida aos poucos", message = "Adicione um pouco de peso ou tempo por semana em vez de avançar rápido demais."),
                "ar" to TipText(title = "تقدم تدريجيًا", message = "أضف قليلًا من الوزن أو الوقت كل أسبوع بدلًا من التقدم بسرعة كبيرة."),
            ),
        ),
        TipModel(
            id = 5,
            texts = mapOf(
                "en" to TipText(
                    title = "Rest Days Matter",
                    message = "Recovery days let your muscles repair and make the next workout stronger.",
                ),
                "de" to TipText(title = "Pausentage zählen", message = "Erholungstage lassen deine Muskeln heilen und stärken dein nächstes Training."),
                "es" to TipText(title = "El descanso importa", message = "Los días de recuperación reparan tus músculos y mejoran el siguiente entrenamiento."),
                "fr" to TipText(title = "Le repos compte", message = "Les jours de récupération réparent vos muscles et renforcent le prochain entraînement."),
                "hi" to TipText(title = "आराम भी ज़रूरी", message = "रिकवरी के दिन मांसपेशियों की मरम्मत करते हैं और अगला वर्कआउट बेहतर बनाते हैं।"),
                "id" to TipText(title = "Hari Istirahat Penting", message = "Hari pemulihan memperbaiki otot dan membuat latihan berikutnya lebih kuat."),
                "pt" to TipText(title = "Descansar é importante", message = "Dias de recuperação reparam os músculos e fortalecem o próximo treino."),
                "ar" to TipText(title = "أيام الراحة مهمة", message = "تسمح أيام التعافي لعضلاتك بالإصلاح وتجعل تمرينك التالي أقوى."),
            ),
        ),
        TipModel(
            id = 6,
            texts = mapOf(
                "en" to TipText(
                    title = "Fuel Your Body",
                    message = "Eat protein and carbs within two hours after training to speed up recovery.",
                ),
                "de" to TipText(title = "Körper versorgen", message = "Iss innerhalb von zwei Stunden nach dem Training Eiweiß und Kohlenhydrate für schnellere Erholung."),
                "es" to TipText(title = "Alimenta tu cuerpo", message = "Come proteínas y carbohidratos en las dos horas posteriores al entrenamiento para recuperarte antes."),
                "fr" to TipText(title = "Nourrissez votre corps", message = "Mangez protéines et glucides dans les deux heures suivant l’entraînement pour mieux récupérer."),
                "hi" to TipText(title = "शरीर को पोषण दें", message = "तेज़ रिकवरी के लिए ट्रेनिंग के दो घंटे के भीतर प्रोटीन और कार्ब्स खाएँ।"),
                "id" to TipText(title = "Beri Tubuh Energi", message = "Makan protein dan karbohidrat dalam dua jam setelah latihan agar pemulihan lebih cepat."),
                "pt" to TipText(title = "Abasteça seu corpo", message = "Consuma proteínas e carboidratos até duas horas após o treino para acelerar a recuperação."),
                "ar" to TipText(title = "غذِ جسمك", message = "تناول البروتين والكربوهيدرات خلال ساعتين بعد التدريب لتسريع التعافي."),
            ),
        ),
    )

    fun observeAll(): Flow<List<TipModel>> {
        return flowOf(tips)
    }
}
