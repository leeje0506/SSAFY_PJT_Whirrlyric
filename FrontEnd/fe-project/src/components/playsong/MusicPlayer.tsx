import { useState, useRef } from "react";
import { songsAPI } from "../../api/songsAPI";
import downloadIcon from "../../assets/icons/playsong/downloadIcon";
import playIcon from "../../assets/icons/playsong/playIcon";
import pauseIcon from "../../assets/icons/playsong/pauseIcon";

interface MusicPlayerProps {
  songId: number;
  title: string;
  songUrl: string;
}

export default function MusicPlayer({ songId, title, songUrl }: MusicPlayerProps) {
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

  const handleEnded = async () => {
    try {
      await songsAPI.countSongPlay(songId);
      handlePlayPause();

    } catch (error) {
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

  const handleDownloadClick = async () => {
    try {
      songUrl = `https://${songUrl}`;
      const response = await songsAPI.downloadSong(songUrl);
      const blob = new Blob([response.data]);
      let fileObjectUrl = window.URL.createObjectURL(blob);
      const link = document.createElement("a");
      link.href = fileObjectUrl;
      link.style.display = "none";
      link.setAttribute("download", `${title}.mp3`);
      document.body.appendChild(link);

      link.click();
      URL.revokeObjectURL(fileObjectUrl);
      link.remove();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="flex flex-col w-96 mx-auto items-center justify-center mt-4">
      <audio
        ref={audioPlayer}
        src={`https://${songUrl}`}
        onLoadedData={handleLoadedData}
        onTimeUpdate={handleTimeUpdate}
        onEnded={handleEnded}
      ></audio>
      <div
        className="w-full relative bg-gray-200 rounded-full h-2.5 cursor-pointer"
        onClick={handleProgressBarClick}
        ref={progressBarRef}
      >
        <div
          className="bg-cyan-500 h-2.5 rounded-full"
          style={{ width: `${(currentTime / duration) * 100}%` }}
        ></div>
        <div
          className="ring-cyan-500 ring-2 absolute w-4 h-4 -mt-3.5 -ml-2 flex items-center justify-center bg-white rounded-full shadow pointer-events-none"
          style={{ left: `${(currentTime / duration) * 100}%` }}
        >
          <div className="w-1.5 h-1.5 bg-cyan-500 rounded-full ring-1 ring-inset ring-slate-900/5 pointer-events-none"></div>
        </div>
      </div>

      <div className="w-full flex mt-6 items-center justify-between">
        <div className="flex-1">
          <div className="px-2 py-2 text-black">
            {formatTime(currentTime)} / {formatTime(duration)}
          </div>
        </div>

        <button
          className="flex-none -my-2 mx-auto w-16 h-16 rounded-full ring-1 ring-slate-900/5 shadow-md flex items-center justify-center"
          onClick={handlePlayPause}
        >
          {isPlaying ? pauseIcon : playIcon}
        </button>

        <div className="flex-1 text-right">
          <button
            className="px-4 py-2 mr-2 bg-black text-white rounded"
            onClick={handleDownloadClick}
          >
            {downloadIcon}
          </button>
        </div>
      </div>
    </div>
  );
}
