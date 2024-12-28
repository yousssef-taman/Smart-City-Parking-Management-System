from flask import Flask, request, jsonify
import mysql.connector

app = Flask(__name__)

DB_CONFIG = {
    "host": "localhost",
    "user": "parking",
    "password": "parking",
    "database": "smartparking"
}

@app.route('/update_reservations', methods=['POST'])
def update_reservations():
    data = request.json
    sample_size = data.get('sample_size', 5)

    try:
        connection = mysql.connector.connect(**DB_CONFIG)
        cursor = connection.cursor()

        select_query = (
            "SELECT spot_id FROM SmartParking.spot as sp WHERE sp.status LIKE 'OCCUPIED' ORDER BY RAND() LIMIT %s;"
        )
        cursor.execute(select_query, (sample_size,))
        sampled_reservations = cursor.fetchall()

        if not sampled_reservations:
            return jsonify({"message": "No reservations to update."}), 404

        update_query = "UPDATE SmartParking.spot as sp SET sp.status = 'AVAILABLE' WHERE spot_id = %s;"
        for reservation in sampled_reservations:
            cursor.execute(update_query, (reservation[0],))

        connection.commit()
        return jsonify({"message": f"{len(sampled_reservations)} reservations updated."}), 200

    except mysql.connector.Error as e:
        return jsonify({"error": str(e)}), 500

    finally:
        if connection.is_connected():
            cursor.close()
            connection.close()

if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=5000)
