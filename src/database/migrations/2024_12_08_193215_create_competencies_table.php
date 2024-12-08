<?php

use App\Http\Enums\StatusEnum;
use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('competencies', function (Blueprint $table) {
            $table->id();


            $table->string('title')->unique()->nullable(false);
            $table->string('description');
            $table->enum('status', StatusEnum::values())->default(StatusEnum::IN_PROGRESS);
            $table->dateTime('start_date');
            $table->dateTime('end_date');
            $table->foreignId('user_id')->constrained('users');

            $table->timestamps();
            $table->softDeletes();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('competencies');
    }
};
