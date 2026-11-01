package shejianguo.app1neard.corpapp.data.repository

import shejianguo.app1neard.corpapp.data.model.Difficulty
import shejianguo.app1neard.corpapp.data.model.ExerciseModel
import shejianguo.app1neard.corpapp.data.model.Intensity
import shejianguo.app1neard.corpapp.data.model.WorkoutModel
import shejianguo.app1neard.corpapp.data.model.WorkoutType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WorkoutRepository {
    private val workouts = listOf<WorkoutModel>(
        WorkoutModel(
            id = 1,
            texts = mapOf("en" to "Bodyweight Basics", "de" to "Körpergewicht Grundlagen", "es" to "Fundamentos Corporales", "fr" to "Bases au Poids du Corps", "hi" to "बॉडीवेट की बुनियाद", "id" to "Dasar Latihan Tubuh", "pt" to "Fundamentos Corporais", "ar" to "أساسيات وزن الجسم"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Bodyweight Squat", "de" to "Kniebeuge", "es" to "Sentadilla", "fr" to "Squat", "hi" to "बॉडीवेट स्क्वाट", "id" to "Squat Tubuh", "pt" to "Agachamento", "ar" to "قرفصاء بوزن الجسم"), null, 3, 12, 45_000L, Intensity.MODERATE),
                ExerciseModel(mapOf("en" to "Knee Push-Up", "de" to "Liegestütz auf Knien", "es" to "Flexión con Rodillas", "fr" to "Pompe sur les Genoux", "hi" to "घुटनों पर पुश-अप", "id" to "Push-Up Lutut", "pt" to "Flexão com Joelhos", "ar" to "تمرين ضغط على الركبتين"), null, 3, 10, 45_000L, Intensity.LOW),
                ExerciseModel(mapOf("en" to "Glute Bridge", "de" to "Beckenheben", "es" to "Puente de Glúteos", "fr" to "Pont Fessier", "hi" to "ग्लूट ब्रिज", "id" to "Jembatan Bokong", "pt" to "Ponte de Glúteos", "ar" to "جسر الأرداف"), null, 3, 15, 30_000L, Intensity.LOW),
                ExerciseModel(mapOf("en" to "Forearm Plank", "de" to "Unterarmstütz", "es" to "Plancha de Antebrazos", "fr" to "Planche sur Avant-Bras", "hi" to "फोरआर्म प्लैंक", "id" to "Plank Lengan Bawah", "pt" to "Prancha de Antebraços", "ar" to "بلانك على الساعدين"), 30_000L, null, null, 30_000L, Intensity.MODERATE),
            ),
            difficulty = Difficulty.BEGINNER,
            type = WorkoutType.STRENGTH,
            imageUrl = "https://images.unsplash.com/photo-1649887974297-4be052375a67?w=800&q=80",
        ),
        WorkoutModel(
            id = 2,
            texts = mapOf("en" to "Core Control", "de" to "Rumpfkontrolle", "es" to "Control del Core", "fr" to "Contrôle du Centre", "hi" to "कोर कंट्रोल", "id" to "Kontrol Inti", "pt" to "Controle do Core", "ar" to "تحكم الجذع"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Dead Bug", "de" to "Käfer", "es" to "Bicho Muerto", "fr" to "Dead Bug", "hi" to "डेड बग", "id" to "Dead Bug", "pt" to "Inseto Morto", "ar" to "الحشرة الميتة"), null, 3, 10, 30_000L, Intensity.LOW),
                ExerciseModel(mapOf("en" to "Bird Dog", "de" to "Diagonalstrecken", "es" to "Perro de Caza", "fr" to "Bird Dog", "hi" to "बर्ड डॉग", "id" to "Bird Dog", "pt" to "Cão de Caça", "ar" to "تمرين الطائر والكلب"), null, 3, 10, 30_000L, Intensity.LOW),
                ExerciseModel(mapOf("en" to "Side Plank", "de" to "Seitstütz", "es" to "Plancha Lateral", "fr" to "Planche Latérale", "hi" to "साइड प्लैंक", "id" to "Plank Samping", "pt" to "Prancha Lateral", "ar" to "بلانك جانبي"), 25_000L, null, null, 30_000L, Intensity.MODERATE),
                ExerciseModel(mapOf("en" to "Reverse Crunch", "de" to "Umgekehrter Crunch", "es" to "Crunch Inverso", "fr" to "Crunch Inversé", "hi" to "रिवर्स क्रंच", "id" to "Crunch Terbalik", "pt" to "Abdominal Inverso", "ar" to "كرنش عكسي"), null, 3, 12, 30_000L, Intensity.MODERATE),
            ),
            difficulty = Difficulty.BEGINNER,
            type = WorkoutType.CORE,
            imageUrl = "https://images.unsplash.com/photo-1571019613531-fbeaeb790845?w=800&q=80",
        ),
        WorkoutModel(
            id = 3,
            texts = mapOf("en" to "Upper Body Builder", "de" to "Oberkörper Aufbau", "es" to "Fuerza del Tren Superior", "fr" to "Renforcement du Haut", "hi" to "अपर बॉडी बिल्डर", "id" to "Pembentuk Tubuh Atas", "pt" to "Força da Parte Superior", "ar" to "بناء الجزء العلوي"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Push-Up", "de" to "Liegestütz", "es" to "Flexión", "fr" to "Pompe", "hi" to "पुश-अप", "id" to "Push-Up", "pt" to "Flexão", "ar" to "تمرين الضغط"), null, 4, 12, 60_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Pike Push-Up", "de" to "Pike-Liegestütz", "es" to "Flexión en Pico", "fr" to "Pompe en Piqué", "hi" to "पाइक पुश-अप", "id" to "Pike Push-Up", "pt" to "Flexão Pike", "ar" to "ضغط بايك"), null, 3, 10, 60_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Diamond Push-Up", "de" to "Diamant-Liegestütz", "es" to "Flexión Diamante", "fr" to "Pompe Diamant", "hi" to "डायमंड पुश-अप", "id" to "Push-Up Berlian", "pt" to "Flexão Diamante", "ar" to "ضغط ماسي"), null, 3, 8, 60_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Plank Shoulder Tap", "de" to "Schultertippen im Stütz", "es" to "Toque de Hombros", "fr" to "Toucher d'Épaules", "hi" to "प्लैंक शोल्डर टैप", "id" to "Sentuh Bahu Plank", "pt" to "Toque nos Ombros", "ar" to "لمس الكتف في البلانك"), null, 3, 16, 45_000L, Intensity.MODERATE),
            ),
            difficulty = Difficulty.INTERMEDIATE,
            type = WorkoutType.UPPER_BODY,
            imageUrl = "https://images.unsplash.com/photo-1509411627497-1f19a8db1854?w=800&q=80",
        ),
        WorkoutModel(
            id = 4,
            texts = mapOf("en" to "Quick Cardio Blast", "de" to "Schneller Cardio-Kick", "es" to "Ráfaga de Cardio", "fr" to "Cardio Express", "hi" to "क्विक कार्डियो ब्लास्ट", "id" to "Ledakan Kardio Cepat", "pt" to "Cardio Rápido", "ar" to "دفعة كارديو سريعة"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Jumping Jack", "de" to "Hampelmann", "es" to "Salto de Tijera", "fr" to "Jumping Jack", "hi" to "जंपिंग जैक", "id" to "Jumping Jack", "pt" to "Polichinelo", "ar" to "قفز فتح وضم"), 40_000L, null, null, 20_000L, Intensity.MODERATE),
                ExerciseModel(mapOf("en" to "High Knees", "de" to "Kniehebelauf", "es" to "Rodillas Altas", "fr" to "Montées de Genoux", "hi" to "हाई नीज़", "id" to "Lari Lutut Tinggi", "pt" to "Joelhos Altos", "ar" to "رفع الركبتين"), 30_000L, null, null, 20_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Mountain Climber", "de" to "Bergsteiger", "es" to "Escalador", "fr" to "Mountain Climber", "hi" to "माउंटेन क्लाइंबर", "id" to "Mountain Climber", "pt" to "Escalador", "ar" to "متسلق الجبال"), 30_000L, null, null, 25_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Skater Jump", "de" to "Skatersprung", "es" to "Salto de Patinador", "fr" to "Saut du Patineur", "hi" to "स्केटर जंप", "id" to "Lompatan Skater", "pt" to "Salto Patinador", "ar" to "قفزة المتزلج"), 30_000L, null, null, 25_000L, Intensity.HIGH),
            ),
            difficulty = Difficulty.INTERMEDIATE,
            type = WorkoutType.HIIT,
            imageUrl = "https://images.unsplash.com/photo-1606903037631-f09fd0bd74b4?w=800&q=80",
        ),
        WorkoutModel(
            id = 5,
            texts = mapOf("en" to "Leg Strength", "de" to "Beinkraft", "es" to "Fuerza de Piernas", "fr" to "Force des Jambes", "hi" to "लेग स्ट्रेंथ", "id" to "Kekuatan Kaki", "pt" to "Força das Pernas", "ar" to "قوة الساقين"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Split Squat", "de" to "Ausfallschrittkniebeuge", "es" to "Sentadilla Dividida", "fr" to "Squat Fendu", "hi" to "स्प्लिट स्क्वाट", "id" to "Split Squat", "pt" to "Agachamento Afundo", "ar" to "قرفصاء منقسمة"), null, 4, 12, 60_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Single-Leg Glute Bridge", "de" to "Einbeiniges Beckenheben", "es" to "Puente a una Pierna", "fr" to "Pont sur une Jambe", "hi" to "सिंगल-लेग ग्लूट ब्रिज", "id" to "Jembatan Bokong Satu Kaki", "pt" to "Ponte Unilateral", "ar" to "جسر أرداف بساق واحدة"), null, 3, 12, 45_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Lateral Lunge", "de" to "Seitlicher Ausfallschritt", "es" to "Zancada Lateral", "fr" to "Fente Latérale", "hi" to "लैटरल लंज", "id" to "Lunge Samping", "pt" to "Afundo Lateral", "ar" to "اندفاع جانبي"), null, 3, 12, 45_000L, Intensity.MODERATE),
                ExerciseModel(mapOf("en" to "Calf Raise", "de" to "Wadenheben", "es" to "Elevación de Talones", "fr" to "Élévation des Mollets", "hi" to "काफ रेज़", "id" to "Angkat Betis", "pt" to "Elevação de Panturrilha", "ar" to "رفع الساق"), null, 4, 20, 30_000L, Intensity.MODERATE),
            ),
            difficulty = Difficulty.INTERMEDIATE,
            type = WorkoutType.STRENGTH,
            imageUrl = "https://images.unsplash.com/photo-1571019613914-85f342c6a11e?w=800&q=80",
        ),
        WorkoutModel(
            id = 6,
            texts = mapOf("en" to "Core Endurance", "de" to "Rumpfausdauer", "es" to "Resistencia del Core", "fr" to "Endurance du Centre", "hi" to "कोर एंड्योरेंस", "id" to "Daya Tahan Inti", "pt" to "Resistência do Core", "ar" to "تحمل الجذع"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Hollow Hold", "de" to "Hollow Hold", "es" to "Posición Hueca", "fr" to "Position Hollow", "hi" to "हॉलो होल्ड", "id" to "Hollow Hold", "pt" to "Posição Hollow", "ar" to "ثبات الجسم المقعر"), 45_000L, null, null, 45_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "V-Up", "de" to "V-Klappmesser", "es" to "Abdominal en V", "fr" to "Abdominal en V", "hi" to "वी-अप", "id" to "V-Up", "pt" to "Abdominal em V", "ar" to "تمرين البطن بشكل V"), null, 4, 12, 60_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Plank Saw", "de" to "Plank-Säge", "es" to "Plancha Sierra", "fr" to "Planche Scie", "hi" to "प्लैंक सॉ", "id" to "Plank Saw", "pt" to "Prancha Serra", "ar" to "بلانك المنشار"), 40_000L, null, null, 45_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Flutter Kick", "de" to "Flatterkick", "es" to "Patada Alterna", "fr" to "Battements de Jambes", "hi" to "फ्लटर किक", "id" to "Tendangan Flutter", "pt" to "Chute Tesoura", "ar" to "رفرفة الساقين"), 45_000L, null, null, 45_000L, Intensity.HIGH),
            ),
            difficulty = Difficulty.ADVANCED,
            type = WorkoutType.CORE,
            imageUrl = "https://images.unsplash.com/photo-1514512364185-4c2b0985be01?w=800&q=80",
        ),
        WorkoutModel(
            id = 7,
            texts = mapOf("en" to "Full Body HIIT", "de" to "Ganzkörper-HIIT", "es" to "HIIT de Cuerpo Completo", "fr" to "HIIT Corps Entier", "hi" to "फुल बॉडी HIIT", "id" to "HIIT Seluruh Tubuh", "pt" to "HIIT de Corpo Inteiro", "ar" to "تمرين HIIT لكامل الجسم"),
            exercises = listOf(
                ExerciseModel(mapOf("en" to "Burpee", "de" to "Burpee", "es" to "Burpee", "fr" to "Burpee", "hi" to "बर्पी", "id" to "Burpee", "pt" to "Burpee", "ar" to "بيربي"), 45_000L, null, null, 30_000L, Intensity.MAXIMUM),
                ExerciseModel(mapOf("en" to "Jump Squat", "de" to "Sprungkniebeuge", "es" to "Sentadilla con Salto", "fr" to "Squat Sauté", "hi" to "जंप स्क्वाट", "id" to "Jump Squat", "pt" to "Agachamento com Salto", "ar" to "قرفصاء بالقفز"), 40_000L, null, null, 30_000L, Intensity.MAXIMUM),
                ExerciseModel(mapOf("en" to "Push-Up", "de" to "Liegestütz", "es" to "Flexión", "fr" to "Pompe", "hi" to "पुश-अप", "id" to "Push-Up", "pt" to "Flexão", "ar" to "تمرين الضغط"), 40_000L, null, null, 30_000L, Intensity.HIGH),
                ExerciseModel(mapOf("en" to "Mountain Climber", "de" to "Bergsteiger", "es" to "Escalador", "fr" to "Mountain Climber", "hi" to "माउंटेन क्लाइंबर", "id" to "Mountain Climber", "pt" to "Escalador", "ar" to "متسلق الجبال"), 45_000L, null, null, 30_000L, Intensity.MAXIMUM),
                ExerciseModel(mapOf("en" to "Tuck Jump", "de" to "Hocksprung", "es" to "Salto Agrupado", "fr" to "Saut Groupé", "hi" to "टक जंप", "id" to "Lompatan Lutut", "pt" to "Salto Agrupado", "ar" to "قفزة ضم الركبتين"), 30_000L, null, null, 45_000L, Intensity.MAXIMUM),
            ),
            difficulty = Difficulty.ADVANCED,
            type = WorkoutType.HIIT,
            imageUrl = "https://images.unsplash.com/photo-1599058917212-d750089bc07e?w=800&q=80",
        ),
    )

    fun observeAll(): Flow<List<WorkoutModel>> {
        return flowOf(workouts)
    }

    fun observeById(id: Int): Flow<WorkoutModel?> {
        val service = workouts.firstOrNull { service -> service.id == id }
        return flowOf(service)
    }

    fun getById(id: Int): WorkoutModel? {
        return workouts.firstOrNull { service -> service.id == id }
    }
}
