import { useState, useRef } from "react";
import { songsAPI } from "../../api/songsAPI";
import downloadIcon from "../../assets/icons/playsong/downloadIcon";
import playIcon from "../../assets/icons/playsong/playIcon";
import pauseIcon from "../../assets/icons/playsong/pauseIcon";

interface MusicPlayerProps {
  title: string;
  songUrl: string;
}

export default function MusicPlayer({ title, songUrl }: MusicPlayerProps) {
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

  const handleDownloadClick = async () => {
    try {
      const response = await songsAPI.downloadSong(songUrl);
      console.log(response.data);
      const blob = new Blob([response.data]);
      const fileObjectUrl = window.URL.createObjectURL(blob);
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
    <div className="flex flex-col items-center justify-center space-y-4">
      <audio
        ref={audioPlayer}
        src={songUrl}
        onLoadedData={handleLoadedData}
        onTimeUpdate={handleTimeUpdate}
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
  
      <div className="w-full flex items-center justify-between">
        <div className="flex-1">
          <div className="px-4 py-2 text-black">
            {formatTime(currentTime)} / {formatTime(duration)}
          </div>
        </div>
  
        <button
          className="flex-none -my-2 mx-auto w-20 h-20 rounded-full ring-1 ring-slate-900/5 shadow-md flex items-center justify-center"
          onClick={handlePlayPause}
        >
          {isPlaying ? pauseIcon : playIcon}
        </button>
  
        <div className="flex-1 text-right">
          <button
            className="px-4 py-2 bg-green-500 text-white rounded"
            onClick={handleDownloadClick}
          >
            {downloadIcon}
          </button>
        </div>
      </div>
    </div>
  );
  
  

  // return (
  //   <div className="flex flex-col items-center justify-center space-y-4">
  //     <audio
  //       ref={audioPlayer}
  //       src={songUrl}
  //       onLoadedData={handleLoadedData}
  //       onTimeUpdate={handleTimeUpdate}
  //     ></audio>
  //     <div
  //       className="w-full relative bg-gray-200 rounded-full h-2.5 cursor-pointer"
  //       onClick={handleProgressBarClick}
  //       ref={progressBarRef}
  //     >
  //       <div
  //         className="bg-cyan-500 h-2.5 rounded-full"
  //         style={{ width: `${(currentTime / duration) * 100}%` }}
  //       ></div>
  //       <div
  //         className="ring-cyan-500 ring-2 absolute w-4 h-4 -mt-3.5 -ml-2 flex items-center justify-center bg-white rounded-full shadow pointer-events-none"
  //         style={{ left: `${(currentTime / duration) * 100}%` }}
  //       >
  //         <div className="w-1.5 h-1.5 bg-cyan-500 rounded-full ring-1 ring-inset ring-slate-900/5 pointer-events-none"></div>
  //       </div>
  //     </div>



  //     {/* <div className="bg-slate-50 text-slate-500 dark:bg-slate-600 dark:text-slate-200 rounded-b-xl flex items-center">
  //       <div className="flex-auto flex items-center justify-evenly">
  //         <button type="button" aria-label="Add to favorites">
  //           <svg width="24" height="24">
  //             <path d="M7 6.931C7 5.865 7.853 5 8.905 5h6.19C16.147 5 17 5.865 17 6.931V19l-5-4-5 4V6.931Z" fill="currentColor" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
  //           </svg>
  //         </button>
  //         <button type="button" className="hidden sm:block lg:hidden xl:block" aria-label="Previous">
  //           <svg width="24" height="24" fill="none">
  //             <path d="m10 12 8-6v12l-8-6Z" fill="currentColor" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
  //             <path d="M6 6v12" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
  //           </svg>
  //         </button>
  //         <button type="button" aria-label="Rewind 10 seconds">
  //           <svg width="24" height="24" fill="none">
  //             <path d="M6.492 16.95c2.861 2.733 7.5 2.733 10.362 0 2.861-2.734 2.861-7.166 0-9.9-2.862-2.733-7.501-2.733-10.362 0A7.096 7.096 0 0 0 5.5 8.226" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
  //             <path d="M5 5v3.111c0 .491.398.889.889.889H9" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
  //           </svg>
  //         </button>
  //       </div>
  //       <button type="button" className="bg-white text-slate-900 dark:bg-slate-100 dark:text-slate-700 flex-none -my-2 mx-auto w-20 h-20 rounded-full ring-1 ring-slate-900/5 shadow-md flex items-center justify-center" aria-label="Pause">
  //         <svg width="30" height="32" fill="currentColor">
  //           <rect x="6" y="4" width="4" height="24" rx="2" />
  //           <rect x="20" y="4" width="4" height="24" rx="2" />
  //         </svg>
  //       </button>
  //       <div className="flex-auto flex items-center justify-evenly">
  //         <button type="button" aria-label="Skip 10 seconds">
  //           <svg width="24" height="24" fill="none">
  //             <path d="M17.509 16.95c-2.862 2.733-7.501 2.733-10.363 0-2.861-2.734-2.861-7.166 0-9.9 2.862-2.733 7.501-2.733 10.363 0 .38.365.711.759.991 1.176" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
  //             <path d="M19 5v3.111c0 .491-.398.889-.889.889H15" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
  //           </svg>
  //         </button>
  //         <button type="button" className="hidden sm:block lg:hidden xl:block" aria-label="Next">
  //           <svg width="24" height="24" fill="none">
  //             <path d="M14 12 6 6v12l8-6Z" fill="currentColor" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
  //             <path d="M18 6v12" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" />
  //           </svg>
  //         </button>
  //         <button type="button" className="rounded-lg text-xs leading-6 font-semibold px-2 ring-2 ring-inset ring-slate-500 text-slate-500 dark:text-slate-100 dark:ring-0 dark:bg-slate-500">
  //           1x
  //         </button>
  //       </div>
  //     </div> */}

  //     <div className="w-full flex items-center space-x-4 bg-gray-400">
  //       <div className="px-4 py-2 bg-blue-500 text-white rounded">
  //         {formatTime(currentTime)} / {formatTime(duration)}
  //       </div>

  //       <button
  //         // className="px-4 py-2 bg-blue-500 text-white rounded"
  //         className="bg-white text-slate-900 dark:bg-slate-100 flex-none -my-2 mx-auto w-20 h-20 rounded-full ring-1 ring-slate-900/5 shadow-md flex items-center justify-center"
  //         onClick={handlePlayPause}
  //       >
  //         {isPlaying ? pauseIcon : playIcon}
  //       </button>
  //       <button
  //         className="px-4 py-2 bg-green-500 text-white rounded"
  //         onClick={handleDownloadClick}
  //       >
  //         {downloadIcon}
  //       </button>
  //     </div>
  //   </div>
  // );
}
