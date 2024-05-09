import { useState, useRef } from "react";

export default function MusicPlayer() {
  const audioPlayer = useRef<HTMLAudioElement>(null);
  const progressBarRef = useRef<HTMLDivElement>(null);
  const [isPlaying, setIsPlaying] = useState(false);
  const [duration, setDuration] = useState(0);
  const [currentTime, setCurrentTime] = useState(0);

  const formatTime = (time: number) => {
    const minutes = Math.floor(time / 60);
    const seconds = Math.floor(time % 60);
    return `${minutes}:${seconds < 10 ? `0${seconds}` : seconds}`;
  };

  const handlePlayPause = () => {
    const audio = audioPlayer.current;
    if (!audio) return;
    if (isPlaying) {
      audio.pause();
    } else {
      audio.play();
    }
    setIsPlaying(!isPlaying);
  };

  const handleLoadedData = () => {
    if (audioPlayer.current) {
      setDuration(audioPlayer.current.duration);
      if (isPlaying) audioPlayer.current.play();
    }
  };

  const handleTimeUpdate = () => {
    if (audioPlayer.current) {
      setCurrentTime(audioPlayer.current.currentTime);
    }
  };

  const handleSkip = (time: number) => {
    if (audioPlayer.current) {
      audioPlayer.current.currentTime = time;
      setCurrentTime(time);
    }
  };

  const handleProgressBarClick = (
    e: React.MouseEvent<HTMLDivElement, MouseEvent>
  ) => {
    const totalWidth = progressBarRef.current?.clientWidth ?? 0;
    const clickedWidth = e.nativeEvent.offsetX;
    const newTime =
      (clickedWidth / totalWidth) * (audioPlayer.current?.duration ?? 0);
    handleSkip(newTime);
  };

  const handleDownloadClick = () => {
    alert("다운로드 기능은 현재 구현되지 않았습니다.");
  };

  return (
    <div className="flex flex-col items-center justify-center space-y-4">
      <audio
        ref={audioPlayer}
        src="https://cdn1.suno.ai/7bb56215-2d23-4b2d-9373-715227de6fe9.mp3"
        onLoadedData={handleLoadedData}
        onTimeUpdate={handleTimeUpdate}
      ></audio>
      <div>
        {formatTime(currentTime)} / {formatTime(duration)}
      </div>
      <div
        className="w-full bg-gray-200 rounded-full h-2.5"
        onClick={handleProgressBarClick}
        ref={progressBarRef}
      >
        <div
          className="bg-blue-600 h-2.5 rounded-full"
          style={{ width: `${(currentTime / duration) * 100}%` }}
        ></div>
      </div>
      <div className="flex space-x-4">
        <button
          className="px-4 py-2 bg-blue-500 text-white rounded"
          onClick={handlePlayPause}
        >
          {isPlaying ? "Pause" : "Play"}
        </button>
        <button
          className="px-4 py-2 bg-green-500 text-white rounded"
          onClick={handleDownloadClick}
        >
          Download
        </button>
      </div>
    </div>
  );
}
